package game.services;

import java.util.List;

/**
 * Created on: 21-Jun-19, 08:07
 *
 * @author: Katy Bucșa
 */

public interface Listener {
    void gameStarted(List<String> player_word);

    void chooseWord(List<String> l);

    void letterGuessed(List<String> l);

    void yourTurn();

    void gameOver(List<String> l);
}
