package utils;

import game.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GPlayer {
    private Player player;
    private List<String> cartiPrimite;
    private List<String> cartiTrimise;
    private List<String> cartiRamase;


    public GPlayer(Player player, List<String> cartiPrimite) {
        this.player = player;
        this.cartiPrimite = new ArrayList<>();
        this.cartiRamase = cartiPrimite;
        this.cartiTrimise = new ArrayList<>();
    }

    public void addToCartiRamase(String carte) {
        this.cartiRamase.add(carte);
    }


    public void addToCartiPrimite(String carte) {
        this.cartiPrimite.add(carte);
    }

    public void addToCartiTrimise(String carte) {
        this.cartiTrimise.add(carte);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<String> getCartiPrimite() {
        return cartiPrimite;
    }

    public void setCartiPrimite(List<String> cartiPrimite) {
        this.cartiPrimite = cartiPrimite;
    }

    public List<String> getCartiTrimise() {
        return cartiTrimise;
    }

    public void setCartiTrimise(List<String> cartiTrimise) {
        this.cartiTrimise = cartiTrimise;
    }

    public List<String> getCartiRamase() {
        return cartiRamase;
    }

    public void setCartiRamase(List<String> cartiRamase) {
        this.cartiRamase = cartiRamase;
    }
}
