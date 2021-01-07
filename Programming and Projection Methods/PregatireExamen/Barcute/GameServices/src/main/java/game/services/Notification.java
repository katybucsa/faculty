package game.services;

import java.io.Serializable;

/**
 * Created on: 21-Jun-19, 13:08
 *
 * @author: Katy Bucșa
 */

public class Notification implements Serializable {
    private Type type;
    private String gameIdentifier;
    private String adversar;
    private int adversarPoz;

    public Notification(Type type, String gameIdentifier, String adversar) {
        this.type = type;
        this.gameIdentifier = gameIdentifier;
        this.adversar = adversar;
    }

    public Notification() {
    }

    public Notification(Type type) {
        this.type = type;
    }

    public Notification(Type type, int adversarPoz) {
        this.type = type;
        this.adversarPoz = adversarPoz;
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

    public String getAdversar() {
        return adversar;
    }

    public void setAdversar(String adversar) {
        this.adversar = adversar;
    }

    public int getAdversarPoz() {
        return adversarPoz;
    }

    public void setAdversarPoz(int adversarPoz) {
        this.adversarPoz = adversarPoz;
    }
}
