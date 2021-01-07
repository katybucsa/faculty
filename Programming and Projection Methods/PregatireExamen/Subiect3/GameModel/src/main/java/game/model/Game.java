package game.model;


import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created on: 13-Jun-19, 16:27
 *
 * @author: Katy Bucșa
 */

@Entity
@Table(name = "Game")
public class Game implements Serializable, HasID<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Column(name = "player1")
    private String player1;

    @Column(name = "player2")
    private String player2;

    @Column(name = "poz1")
    private int poz1;

    @Column(name = "poz2")
    private int poz2;

    @Column(name = "winner")
    private String winner;


    public Game() {
    }

    public Game(String player1, String player2, int poz1, int poz2) {
        this.player1 = player1;
        this.player2 = player2;
        this.poz1 = poz1;
        this.poz2 = poz2;
    }

    @Override
    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getPoz1() {
        return poz1;
    }

    public void setPoz1(int poz1) {
        this.poz1 = poz1;
    }

    public int getPoz2() {
        return poz2;
    }

    public void setPoz2(int poz2) {
        this.poz2 = poz2;
    }
}
