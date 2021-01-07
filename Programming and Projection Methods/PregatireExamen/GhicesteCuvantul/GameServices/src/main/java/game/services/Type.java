package game.services;

import java.io.Serializable;

/**
 * Created on: 21-Jun-19, 09:50
 *
 * @author: Katy Bucșa
 */

public enum Type implements Serializable {
    CHOOSE_WORD, GAME_STARTED,LETTER_GUESSED, YOUR_TURN, GAME_OVER
}
