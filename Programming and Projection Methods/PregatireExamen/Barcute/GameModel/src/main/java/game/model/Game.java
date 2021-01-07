package game.model;

import org.codehaus.jackson.annotate.JsonIgnore;

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
    private Integer id;

    @Column(name = "player1")
    private String player1;

    @Column(name = "player2")
    private String player2;

    @Column(name = "poz1")
    private String poz1;

    @Column(name = "poz2")
    private String poz2;

    @Column(name = "nrInc1")
    private int nrInc1;

    @Column(name = "nrInc2")
    private int nrInc2;

    @Column(name = "winner")
    private String winner;


    public Game() {
    }

    public Game(String player1, String player2, String poz1, String poz2, int nrInc1, int nrInc2, String winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.poz1 = poz1;
        this.poz2 = poz2;
        this.nrInc1 = nrInc1;
        this.nrInc2 = nrInc2;
        this.winner = winner;
    }

    @Override
    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @Override
    @JsonIgnore
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

    public String getPoz1() {
        return poz1;
    }

    public void setPoz1(String poz1) {
        this.poz1 = poz1;
    }

    public String getPoz2() {
        return poz2;
    }

    public void setPoz2(String poz2) {
        this.poz2 = poz2;
    }

    public int getNrInc1() {
        return nrInc1;
    }

    public void setNrInc1(int nrInc1) {
        this.nrInc1 = nrInc1;
    }

    public int getNrInc2() {
        return nrInc2;
    }

    public void setNrInc2(int nrInc2) {
        this.nrInc2 = nrInc2;
    }
}
