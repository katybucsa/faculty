package game.server;

import game.model.Game;
import game.model.Player;
import game.persistance.IGameRepository;
import game.persistance.IPlayerRepository;
import game.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import utils.GPlayer;

import java.rmi.RemoteException;
import java.util.Arrays;
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
    private Map<Player, String> waitingPlayers;
    private Map<String, List<GPlayer>> activeGames;

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
            if (x.get(0).getPlayer().getId().equals(p.getId()))
                notifyObservers(new Notification(Type.YOU_WON), x.get(1).getPlayer().getId());
            else if (x.get(1).getPlayer().getId().equals(p.getId()))
                notifyObservers(new Notification(Type.YOU_WON), x.get(0).getPlayer().getId());
        });
    }

    @Override
    public synchronized void startGame(Player player, int poz1, int poz2) {
        if (waitingPlayers.size() == 0) {
            waitingPlayers.put(player, "" + poz1 + "," + poz2);
            return;
        }
        List<Player> l = waitingPlayers.keySet().stream().collect(Collectors.toList());
        int p = new Random().nextInt(l.size());

        //player 1 detailes
        Player first = l.get(p);
        String firstpoz = waitingPlayers.remove(first);
        String secondpoz = poz1 + "," + poz2;
        GPlayer gp1 = new GPlayer(first, firstpoz);
        GPlayer gp2 = new GPlayer(player, secondpoz);

        String identifier = "1" + first.getId() + "2" + player.getId();
        activeGames.put(identifier, Arrays.asList(gp1, gp2));

        notifyObservers(new Notification(Type.GAME_STARTED, identifier, first.getUsername()), player.getId());
        notifyObservers(new Notification(Type.GAME_STARTED, identifier, player.getUsername()), first.getId());
        notifyObservers(new Notification(Type.YOUR_TURN), first.getId());
        notifyObservers(new Notification(Type.OTHER_TURN), player.getId());
    }

    private int gameOver(GPlayer gp1, GPlayer gp2, int poz) {
        gp1.setNrInc(gp1.getNrInc() + 1);

        String[] pozitiiS = gp2.getPozitie().split(",");
        int[] pozitii = new int[2];
        pozitii[0] = Integer.parseInt(pozitiiS[0]);
        pozitii[1] = Integer.parseInt(pozitiiS[1]);

        int ghicit = 0;
        if (poz == pozitii[0] && !gp2.getPozitiiG().contains(poz)) {
            ghicit = 1;
            gp2.addGPoz(poz);
            if (gp2.getPozitiiG().size() == 2) {
                ghicit = 2;
            }
        }
        if (poz == pozitii[1] && !gp2.getPozitiiG().contains(poz)) {
            ghicit = 1;
            gp2.addGPoz(poz);
            if (gp2.getPozitiiG().size() == 2) {
                ghicit = 2;
            }
        }
        if (ghicit == 1) {
            notifyObservers(new Notification(Type.GUESSED_ONEP, poz), gp1.getPlayer().getId());
            notifyObservers(new Notification(Type.YOUR_TURN, poz), gp2.getPlayer().getId());
        } else if (ghicit == 2) {
            notifyObservers(new Notification(Type.YOU_WON), gp1.getPlayer().getId());
            notifyObservers(new Notification(Type.YOU_LOST, poz), gp2.getPlayer().getId());
            Game game = new Game(gp1.getPlayer().getId(), gp2.getPlayer().getId(), gp1.getPozitie(), gp2.getPozitie(), gp1.getNrInc(), gp2.getNrInc(), gp1.getPlayer().getId());
            gameRepo.save(game);
        }
        return ghicit;
    }

    @Override
    public synchronized void attack(String gameIdentifier, Player player, int poz) {
        List<GPlayer> gPlayers = activeGames.get(gameIdentifier);
        int over = 0;
        //e primul jucator
        if (gameIdentifier.contains("1" + player.getId())) {
            over = gameOver(gPlayers.get(0), gPlayers.get(1), poz);
            if (over == 2)
                activeGames.remove(gameIdentifier);
            if (over == 0) {
                notifyObservers(new Notification(Type.YOUR_TURN, poz), gPlayers.get(1).getPlayer().getId());
                return;
            }
        }
        //e al doilea jucator
        else {
            over = gameOver(gPlayers.get(1), gPlayers.get(0), poz);
            if (over == 2)
                activeGames.remove(gameIdentifier);
            if (over == 0)
                notifyObservers(new Notification(Type.YOUR_TURN, poz), gPlayers.get(0).getPlayer().getId());
        }
    }

    public void notifyObservers(Notification notification, String player) {
        try {
            loggedPlayers.get(player).update(notification);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
