package game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created on: 23-Jun-19, 09:19
 *
 * @author: Katy Bucșa
 */
@Entity
@Table(name = "GamePlayer")
public class GamePlayer implements Serializable{
    @Id
    @Column(name = "game")
    private int game;
    @Id
    @Column(name = "player")
    private String player;

    @Column(name = "letters")
    private String letters;

    @Column(name = "noPoints")
    private int noPoints;

    public GamePlayer(int game, String player, String letters, int noPoints) {
        this.game = game;
        this.player = player;
        this.letters = letters;
        this.noPoints = noPoints;
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

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public int getNoPoints() {
        return noPoints;
    }

    public void setNoPoints(int noPoints) {
        this.noPoints = noPoints;
    }
}
