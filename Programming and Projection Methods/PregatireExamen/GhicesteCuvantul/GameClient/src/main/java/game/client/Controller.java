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

    public void startChooseWords(String word) {
        server.wordChoosen(this.player, word);
    }

    public void attack(String user_cuv, String letter) {
        server.attack(this.player, user_cuv, letter);
    }

    @Override
    public void update(Notification notif) throws AppException {
        Platform.runLater(() -> {
            if (notif.getType() == Type.GAME_STARTED) {
                listener.gameStarted(notif.getParticipants());
            }
            if (notif.getType() == Type.CHOOSE_WORD) {
                listener.chooseWord(notif.getParticipants());
            }
            if (notif.getType() == Type.LETTER_GUESSED) {
                listener.letterGuessed(notif.getParticipants());
            }
            if (notif.getType() == Type.YOUR_TURN) {
                listener.yourTurn();
            }
            if (notif.getType() == Type.GAME_OVER) {
                listener.gameOver(notif.getParticipants());
            }
        });
    }
}
