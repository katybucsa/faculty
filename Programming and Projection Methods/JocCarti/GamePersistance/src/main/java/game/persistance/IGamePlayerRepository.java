package game.persistance;

import game.model.GamePlayer;

import java.util.List;

/**
 * Created on: 23-Jun-19, 09:28
 *
 * @author: Katy Bucșa
 */

public interface IGamePlayerRepository extends IRepository<Integer, GamePlayer> {
    List<GamePlayer> findPlayerGameContribution(int gameId);
}
