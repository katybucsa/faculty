package game.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on: 13-Jun-19, 16:30
 *
 * @author: Katy Bucșa
 */

@Entity
@Table(name = "Player")
public class Player implements Serializable, HasID<String> {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player() {
    }

    @Override
    public String getId() {
        return username;
    }

    @Override
    public void setId(String s) {
        this.username = s;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
