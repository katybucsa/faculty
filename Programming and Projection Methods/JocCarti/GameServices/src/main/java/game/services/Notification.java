package game.services;

import java.io.Serializable;
import java.util.List;

/**
 * Created on: 21-Jun-19, 13:08
 *
 * @author: Katy Bucșa
 */

public class Notification implements Serializable {
    private Type type;
    private String gameIdentifier;
    private List<String> participants;
    private List<String> receivedCarts;
    private String winner;
    private String cartePrimita;


    public Notification() {
    }

    public Notification(Type type) {
        this.type = type;
    }

    public Notification(Type type, String gameIdentifier, List<String> participants, List<String> receivedCarts) {
        this.type = type;
        this.gameIdentifier = gameIdentifier;
        this.participants = participants;
        this.receivedCarts = receivedCarts;
    }

    public Notification(Type type, String winner) {
        this.type = type;
        this.winner = winner;
    }

    public Notification(Type type, List<String> receivedCarts) {
        this.type = type;
        this.receivedCarts = receivedCarts;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getGameIdentifier() {
        return gameIdentifier;
    }

    public void setGameIdentifier(String gameIdentifier) {
        this.gameIdentifier = gameIdentifier;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<String> getReceivedCarts() {
        return receivedCarts;
    }

    public void setReceivedCarts(List<String> receivedCarts) {
        this.receivedCarts = receivedCarts;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getCartePrimita() {
        return cartePrimita;
    }

    public void setCartePrimita(String cartePrimita) {
        this.cartePrimita = cartePrimita;
    }
}
