package utils;

import game.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on: 22-Jun-19, 22:08
 *
 * @author: Katy Bucșa
 */

public class GPlayer {
    private Player player;
    private String pozitie;
    private int nrInc;
    private List<Integer> pozitiiG;


    public GPlayer(Player player, String pozitie) {
        this.player = player;
        this.pozitie = pozitie;
        this.pozitiiG = new ArrayList<>();
        this.nrInc = 0;
    }

    public void addGPoz(int poz) {
        this.pozitiiG.add(poz);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getPozitie() {
        return pozitie;
    }

    public void setPozitie(String pozitie) {
        this.pozitie = pozitie;
    }

    public int getNrInc() {
        return nrInc;
    }

    public void setNrInc(int nrInc) {
        this.nrInc = nrInc;
    }

    public List<Integer> getPozitiiG() {
        return pozitiiG;
    }

    public void setPozitiiG(List<Integer> pozitiiG) {
        this.pozitiiG = pozitiiG;
    }
}
