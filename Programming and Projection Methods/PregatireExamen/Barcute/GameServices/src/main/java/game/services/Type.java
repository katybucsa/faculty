package game.services;

import java.io.Serializable;

/**
 * Created on: 21-Jun-19, 09:50
 *
 * @author: Katy Bucșa
 */

public enum Type implements Serializable {
    GAME_STARTED, YOUR_TURN, OTHER_TURN, YOU_WON, YOU_LOST,GUESSED_ONEP
}
