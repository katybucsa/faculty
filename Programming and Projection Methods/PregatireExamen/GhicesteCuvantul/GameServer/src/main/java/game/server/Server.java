package game.server;

import game.model.Game;
import game.model.GamePlayer;
import game.model.Player;
import game.persistance.IGamePlayerRepository;
import game.persistance.IGameRepository;
import game.persistance.IPlayerRepository;
import game.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import utils.GPlayer;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Server implements IServer {
    private IPlayerRepository playerRepo;
    @Autowired
    private IGameRepository gameRepo;
    @Autowired
    private IGamePlayerRepository gamePlayerRepo;
    private int turn = -1;
    private Map<String, IObserver> loggedPlayers;
    private List<GPlayer> activePlayers;

    public Server(IPlayerRepository playerRepo, IGameRepository gameRepo, IGamePlayerRepository gamePlayerRepo) {
        this.playerRepo = playerRepo;
        this.gameRepo = gameRepo;
        this.gamePlayerRepo = gamePlayerRepo;
        loggedPlayers = new ConcurrentHashMap<>();
        activePlayers = new ArrayList<>();
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
        if (turn != -1)
            throw new AppException("Joc in desfasurare...incercati din nou mai tarziu");
        if (loggedPlayers.size() == 1)
            throw new AppException("Nu sunt jucatori logati...incercati din nou mai tarziu");
        turn = loggedPlayers.size();
        List<String> l = new ArrayList<>();
        loggedPlayers.keySet().forEach(x -> l.add(x));
        loggedPlayers.keySet().forEach(x -> notifyObserver(new Notification(Type.CHOOSE_WORD, l), x));
    }

    @Override
    public synchronized void wordChoosen(Player player, String word) {
        GPlayer gPlayer = new GPlayer(player, word);
        activePlayers.add(gPlayer);
        if (activePlayers.size() != turn)
            return;
        List<String> cuvs = new ArrayList<>();
        activePlayers.forEach(x -> cuvs.add(x.getWord()));
        cuvs.forEach(c -> activePlayers.forEach(x -> x.addCuvToList(c)));
        //primul jucator din lista va inncepe jocul
        turn = 0;
        ExecutorService executor = Executors.newFixedThreadPool(activePlayers.size());
        //se trimite fiecarui jucator notificare ca a inceput jocul si cuvantul ales de fiecare participant
        notifyAll(Type.GAME_STARTED);
        notifyObserver(new Notification(Type.YOUR_TURN), activePlayers.get(turn).getPlayer().getId());
        ++turn;
    }


    @Override
    public synchronized void attack(Player player, String user_cuv, String letter) {
        GPlayer attackedPlayer =
                activePlayers.stream().filter(x -> x.getPlayer().getId().equals(user_cuv)).findFirst().get();
        GPlayer attackerPlayer =
                activePlayers.stream().filter(x -> x.getPlayer().getId().equals(player.getId())).findFirst().get();
        attackerPlayer.addTryLetter(letter);
        //activePlayers.set(turn, attackerPlayer);
        String word = attackedPlayer.getWord();
        String codedWord = "";
        if (word.contains(letter) && !attackedPlayer.getCuv_listLit().get(word).contains(letter)) {
            for (int i = 0; i < word.length(); i++)
                if (letter.equals("" + word.charAt(i)))
                    codedWord += letter;
                else
                    codedWord += "" + attackedPlayer.getCodedWord().charAt(i);
            int idx = activePlayers.indexOf(attackedPlayer);
            attackedPlayer.setCodedWord(codedWord);
            activePlayers.set(idx, attackedPlayer);
            attackerPlayer.appendToListLit(word, letter);
            notifyAll(Type.LETTER_GUESSED);

            //verifica game over
            if (gameOver()) {
                return;
            }
        }
        if (turn == activePlayers.size()) {
            turn = 0;
        }
        notifyObserver(new Notification(Type.YOUR_TURN), activePlayers.get(turn).getPlayer().getId());
        ++turn;
    }

    private boolean gameOver() {
        boolean over = true;
        List<String> punctaje = new ArrayList<>();
        int max = 0;
        String winner = "";
        for (int i = 0; i < activePlayers.size(); i++) {
            int punctaj = 0;
            GPlayer gp = activePlayers.get(i);
            List<List<String>> l = gp.getCuv_listLit().values().stream().collect(Collectors.toList());
            for (int j = 0; j < l.size(); j++)
                punctaj += l.get(j).size();
            if (punctaj > max) {
                max = punctaj;
                winner = gp.getPlayer().getId();
            }
            gp.setNrPoints(punctaj);
            activePlayers.set(i, gp);
            punctaje.add(gp.getPlayer().getId() + ": " + punctaj);
            if (gp.getCodedWord().contains("C") || activePlayers.get(i).getCodedWord().contains("V"))
                over = false;

        }
        if (over) {
            Game game = new Game(winner);
            gameRepo.save(game);
            game = gameRepo.findLastAdded();
            int id = game.getId();
            activePlayers.forEach(x -> {
                GamePlayer gp = new GamePlayer(id, x.getPlayer().getId(), x.getLiterePropuse(), x.getNrPoints());
                gamePlayerRepo.save(gp);
            });
            ExecutorService executor = Executors.newFixedThreadPool(activePlayers.size());
            activePlayers.forEach(x -> executor.execute(() ->
                    notifyObserver(new Notification(Type.GAME_OVER, punctaje), x.getPlayer().getId())));
            activePlayers.clear();
        }
        return over;
    }


    private void notifyAll(Type type) {
        ExecutorService executor = Executors.newFixedThreadPool(activePlayers.size());
        for (int i = 0; i < activePlayers.size(); i++) {
            GPlayer gp = activePlayers.get(i);
            List<GPlayer> gplay = new ArrayList<>(activePlayers);
            gplay.remove(gp);
            List<String> cuv_juc = new ArrayList<>();
            gplay.forEach(x -> cuv_juc.add(x.getPlayer().getId() + ": " + x.getCodedWord()));

            executor.execute(() -> notifyObserver(new Notification(type, cuv_juc), gp.getPlayer().getId()));
        }
        executor.shutdown();
    }

    public void notifyObserver(Notification notification, String player) {
        try {
            loggedPlayers.get(player).update(notification);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
