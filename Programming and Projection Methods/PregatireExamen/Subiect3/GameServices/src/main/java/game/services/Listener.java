package game.services;

/**
 * Created on: 21-Jun-19, 08:07
 *
 * @author: Katy Bucșa
 */

public interface Listener {
    void gameStarted(String adversar);

    void waitingForPlayer();

    void yourTurn(int altp);

    void otherTurn();

    void gameWon();

    void gameLost();
}
