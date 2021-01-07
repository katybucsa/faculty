package game.server;

import game.model.Game;
import game.model.Player;
import game.persistance.IGameRepository;
import game.persistance.IPlayerRepository;
import game.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Server implements IServer {
    private IPlayerRepository playerRepo;
    @Autowired
    private IGameRepository gameRepo;
    private Map<String, IObserver> loggedPlayers;
    private Map<Player, Integer> waitingPlayers;
    private Map<String, Game> activeGames;

    public Server(IPlayerRepository playerRepo, IGameRepository gameRepo) {
        this.playerRepo = playerRepo;
        this.gameRepo = gameRepo;
        loggedPlayers = new ConcurrentHashMap<>();
        waitingPlayers = new ConcurrentHashMap<>();
        activeGames = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void login(Player u, IObserver o) {
        if (loggedPlayers.containsKey(u.getId()))
            throw new AppException("Utilizator deja logat!");
        Player player = playerRepo.findOne(u);
        if (player == null)
            throw new AppException("Date invalide!");
        loggedPlayers.put(u.getId(), o);
    }

    @Override
    public synchronized void logout(Player u, IObserver o) {
        verifyActiveGame(u);
        loggedPlayers.remove(u.getId(), o);
    }

    private void verifyActiveGame(Player p) {
        activeGames.values().forEach(x -> {
            if (x.getPlayer1().equals(p.getId()))
                notifyObservers(new Notification(Type.YOU_WON), x.getPlayer2());
            else if (x.getPlayer2().equals(p.getId()))
                notifyObservers(new Notification(Type.YOU_WON), x.getPlayer1());
        });
    }

    @Override
    public synchronized void startGame(Player player, int poz) {
        if (waitingPlayers.size() == 0) {
            waitingPlayers.put(player, poz);
            notifyObservers(new Notification(Type.WAITING_FOR_PLAYER), player.getId());
            return;
        }
        List<Player> l = waitingPlayers.keySet().stream().collect(Collectors.toList());
        int p = new Random().nextInt(l.size());

        //player 1 detailes
        Player first = l.get(p);
        int firstpoz = waitingPlayers.remove(first);
        Game game = new Game(first.getId(), player.getId(), firstpoz, poz);
        String identifier = "1" + first.getId() + "2" + player.getId();
        activeGames.put(identifier, game);

        notifyObservers(new Notification(Type.GAME_STARTED, identifier, first.getUsername()), player.getId());
        notifyObservers(new Notification(Type.GAME_STARTED, identifier, player.getUsername()), first.getId());
        notifyObservers(new Notification(Type.YOUR_TURN), first.getId());
        notifyObservers(new Notification(Type.OTHER_TURN), player.getId());
    }


    private boolean gameOver(String gameIdentifier, Player player, int poz) {
        Game game = activeGames.get(gameIdentifier);
        String winner = null, loser = null;

        //e primul jucator
        if (gameIdentifier.contains("1" + player.getId())) {
            if (game.getPoz2() == poz) {
                winner = game.getPlayer1();
                loser = game.getPlayer2();
            }
        }
        //e al doilea jucator
        else {
            if (game.getPoz1() == poz) {
                winner = game.getPlayer2();
                loser = game.getPlayer2();
            }
        }
        if (winner != null) {
            notifyObservers(new Notification(Type.YOU_WON), winner);
            notifyObservers(new Notification(Type.YOU_LOST), loser);
            game.setWinner(winner);
            gameRepo.save(game);
            activeGames.remove(gameIdentifier);
            return true;
        }
        return false;
    }

    @Override
    public synchronized void attack(String gameIdentifier, Player player, int poz) {
        Game game = activeGames.get(gameIdentifier);
        if (gameOver(gameIdentifier, player, poz))
            return;

        if (!game.getPlayer1().equals(player.getId())) {
            notifyObservers(new Notification(Type.YOUR_TURN, poz), game.getPlayer1());
            return;
        }
        notifyObservers(new Notification(Type.YOUR_TURN, poz), game.getPlayer2());
    }

    public void notifyObservers(Notification notification, String player) {
        try {
            loggedPlayers.get(player).update(notification);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
