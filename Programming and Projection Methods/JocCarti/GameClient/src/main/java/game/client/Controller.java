package game.client;

import game.services.*;

import game.model.*;
import javafx.application.Platform;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Controller extends UnicastRemoteObject implements IObserver, Serializable {
    private IServer server;
    private Player player;
    private String currentGameIdentifier;
    private Listener listener;

    public Controller(IServer serv) throws RemoteException {
        this.server = serv;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }


    public void findUser(String username, String password) {
        Player player = new Player(username, password);
        server.login(player, this);
        this.player = player;
    }

    public void logout(Player player) {
        server.logout(player, this);
    }


    public void startGame() {
        server.startGame(player);
    }

    public void attack(String carte) {
        server.attack(currentGameIdentifier, this.player, carte);
    }

    @Override
    public void update(Notification notif) throws AppException {
        Platform.runLater(() -> {
            if (notif.getType() == Type.GAME_STARTED) {
                this.currentGameIdentifier = notif.getGameIdentifier();
                listener.gameStarted(notif.getParticipants(), notif.getReceivedCarts());
            }
            if (notif.getType() == Type.YOUR_TURN) {
                listener.yourTurn();
            }
            if (notif.getType() == Type.GAME_OVER) {
                listener.gameOver(notif.getWinner());
            }
            if (notif.getType() == Type.CARTE_PRIMITA) {
                listener.cartePrimita(notif.getReceivedCarts());
            }
        });
    }
}
