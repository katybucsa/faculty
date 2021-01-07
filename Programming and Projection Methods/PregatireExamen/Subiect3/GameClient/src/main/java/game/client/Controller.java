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


    public void startGame(int poz) {
        server.startGame(player, poz);
    }

    public void attack(int poz) {
        server.attack(currentGameIdentifier, player, poz);
    }

    @Override
    public void update(Notification notif) throws AppException {
        Platform.runLater(() -> {
            if (notif.getType() == Type.WAITING_FOR_PLAYER) {
                listener.waitingForPlayer();
            }
            if (notif.getType() == Type.GAME_STARTED) {
                this.currentGameIdentifier = notif.getGameIdentifier();
                listener.gameStarted(notif.getAdversar());
            }
            if (notif.getType() == Type.YOUR_TURN) {
                listener.yourTurn(notif.getAdversarPoz());
            }
            if (notif.getType() == Type.OTHER_TURN) {
                listener.otherTurn();
            }

            if (notif.getType() == Type.YOU_LOST) {
                listener.gameLost();
            }
            if (notif.getType() == Type.YOU_WON) {
                listener.gameWon();
            }
        });
    }
}
