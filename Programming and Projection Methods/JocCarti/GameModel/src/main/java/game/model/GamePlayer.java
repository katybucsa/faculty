package game.model;

import game.model.Game;
import game.model.Player;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on: 24-Jun-19, 08:34
 *
 * @author: Katy Bucșa
 */
@Entity
@Table(name = "GamePlayer")
public class GamePlayer implements Serializable {
    @Id
    @Column(name = "game")
    private int game;

    @Id
    @Column(name = "player")
    private String player;

    @Column(name = "cartiPrimite")
    private String cartiPrimite;

    @Column(name = "cartiTrimise")
    private String cartiTrimise;


    public GamePlayer(int game, String player, String cartiPrimite, String cartiTrimise) {
        this.game = game;
        this.player = player;
        this.cartiPrimite = cartiPrimite;
        this.cartiTrimise = cartiTrimise;
    }

    public GamePlayer() {
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getCartiPrimite() {
        return cartiPrimite;
    }

    public void setCartiPrimite(String cartiPrimite) {
        this.cartiPrimite = cartiPrimite;
    }

    public String getCartiTrimise() {
        return cartiTrimise;
    }

    public void setCartiTrimise(String cartiTrimise) {
        this.cartiTrimise = cartiTrimise;
    }
}
