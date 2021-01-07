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

    @Column(name = "winner")
    private String winner;


    public Game() {
    }

    public Game(String winner) {
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
}
