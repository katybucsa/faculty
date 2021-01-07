package game.services;

import java.util.List;


public interface Listener {
    void gameStarted(List<String> participants, List<String> carti);

    void yourTurn();

    void gameOver(String winner);

    void cartePrimita(List<String> carti);
}
