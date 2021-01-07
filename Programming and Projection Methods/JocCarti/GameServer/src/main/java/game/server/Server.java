package game.server;

import com.sun.org.apache.xml.internal.utils.StringToIntTable;
import game.model.Game;
import game.model.GamePlayer;
import game.model.Player;
import game.persistance.IGamePlayerRepository;
import game.persistance.IGameRepository;
import game.persistance.IPlayerRepository;
import game.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import sun.plugin.util.NotifierObject;
import utils.GPlayer;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements IServer {
    private IPlayerRepository playerRepo;
    @Autowired
    private IGameRepository gameRepo;
    @Autowired
    private IGamePlayerRepository gamePlayerRepo;
    private List<String> carti = Arrays.asList("J", "J", "J", "Q", "Q", "Q", "K", "K", "K", "Jocker");
    private Map<String, IObserver> loggedPlayers;
    private List<Player> waitingPlayers;
    private Map<String, List<GPlayer>> activeGames;
    private Map<String, Integer> gameTurn;

    public Server(IPlayerRepository playerRepo, IGameRepository gameRepo, IGamePlayerRepository gamePlayerRepo) {
        this.playerRepo = playerRepo;
        this.gameRepo = gameRepo;
        this.gamePlayerRepo = gamePlayerRepo;
        loggedPlayers = new ConcurrentHashMap<>();
        waitingPlayers = new ArrayList<>();
        activeGames = new ConcurrentHashMap<>();
        gameTurn = new ConcurrentHashMap<>();
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
//        activeGames.values().forEach(x -> {
//            if (x.get(0).getPlayer().getId().equals(p.getId()))
//                notifyObserver(new Notification(Type.YOU_WON), x.get(1).getPlayer().getId());
//            else if (x.get(1).getPlayer().getId().equals(p.getId()))
//                notifyObserver(new Notification(Type.YOU_WON), x.get(0).getPlayer().getId());
//        });
    }

    @Override
    public synchronized void startGame(Player player) {
        waitingPlayers.add(player);
        if (waitingPlayers.size() < 3) {
            return;
        }
        List<String> participants = new ArrayList<>();
        for (int i = 0; i < waitingPlayers.size(); i++) {
            participants.add(waitingPlayers.get(i).getId());
        }
        List<String> suffledCarts = new ArrayList<>(carti);
        Collections.shuffle(suffledCarts);
        List<String> cprimite1 = new ArrayList<>();
        List<String> cprimite2 = new ArrayList<>();
        List<String> cprimite3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cprimite1.add(suffledCarts.get(i));
        }
        for (int i = 3; i < 6; i++) {
            cprimite2.add(suffledCarts.get(i));
        }
        for (int i = 6; i < 9; i++) {
            cprimite3.add(suffledCarts.get(i));
        }
        cprimite2.add(suffledCarts.get(9));
        GPlayer gp1 = new GPlayer(waitingPlayers.get(0), cprimite1);
        GPlayer gp2 = new GPlayer(waitingPlayers.get(1), cprimite2);
        GPlayer gp3 = new GPlayer(waitingPlayers.get(2), cprimite3);

        String gameIdentifier = waitingPlayers.get(0).getId();

        activeGames.put(gameIdentifier, Arrays.asList(gp1, gp2, gp3));
        gameTurn.put(gameIdentifier, 1);

        notifyObserver(new Notification(Type.GAME_STARTED, gameIdentifier, participants, cprimite1), waitingPlayers.get(0).getId());
        notifyObserver(new Notification(Type.GAME_STARTED, gameIdentifier, participants, cprimite2), waitingPlayers.get(1).getId());
        notifyObserver(new Notification(Type.GAME_STARTED, gameIdentifier, participants, cprimite3), waitingPlayers.get(2).getId());
        notifyObserver(new Notification(Type.YOUR_TURN), waitingPlayers.get(1).getId());
        waitingPlayers.clear();
    }

    private boolean gameOver(List<GPlayer> list) {
        String winner = "";
        for (int i = 0; i < list.size(); i++) {
            GPlayer gp = list.get(i);
            if (gp.getCartiRamase().stream().distinct().count() <= 1) {
                winner = gp.getPlayer().getId();

            }
        }
        final String win = winner;
        if (win != "") {
            Game game = new Game(winner);
            gameRepo.save(game);
            game = gameRepo.findLastAdded();

            for (int i = 0; i < list.size(); i++) {
                String cartiPrimite = "";
                String cartiTrimise = "";
                for (int j = 0; j < list.get(i).getCartiPrimite().size(); j++)
                    cartiPrimite += list.get(i).getCartiPrimite().get(j) + ",";
                for (int j = 0; j < list.get(i).getCartiTrimise().size(); j++)
                    cartiPrimite += list.get(i).getCartiTrimise().get(j) + ",";
                GamePlayer gp = new GamePlayer(game.getId(), list.get(i).getPlayer().getId(), cartiPrimite, cartiTrimise);
                gamePlayerRepo.save(gp);
            }
            ExecutorService executor = Executors.newFixedThreadPool(list.size());
            for (int i = 0; i < list.size(); i++) {
                GPlayer g = list.get(i);
                executor.execute(() -> notifyObserver(new Notification(Type.GAME_OVER, win), g.getPlayer().getId()));

            }
            return true;
        }
        return false;
    }

    @Override
    public synchronized void attack(String gameIdentifier, Player player, String carte) {
        List<GPlayer> gPlayers = activeGames.get(gameIdentifier);
        int turn = gameTurn.get(gameIdentifier);
        GPlayer sender = gPlayers.get(turn);
        List<String> cRamase = sender.getCartiRamase();
        List<String> cTrimise = sender.getCartiTrimise();
        cTrimise.add(carte);
        cRamase.remove(carte);
        sender.setCartiRamase(cRamase);
        sender.setCartiTrimise(cTrimise);
        gPlayers.set(gameTurn.get(gameIdentifier), sender);
        if (gameOver(gPlayers))
            return;


        if (turn == 2) {
            turn = 0;
        } else {
            ++turn;
        }
        gameTurn.put(gameIdentifier, turn);
        GPlayer receiver = gPlayers.get(turn);
        receiver.addToCartiPrimite(carte);
        receiver.addToCartiRamase(carte);
        gPlayers.set(turn, receiver);

        notifyObserver(new Notification(Type.CARTE_PRIMITA, receiver.getCartiRamase()), receiver.getPlayer().getId());
    }

    public void notifyObserver(Notification notification, String player) {
        try {
            loggedPlayers.get(player).update(notification);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
