package game.persistance;

import game.model.GamePlayer;

/**
 * Created on: 23-Jun-19, 09:28
 *
 * @author: Katy Bucșa
 */

public interface IGamePlayerRepository extends IRepository<Integer, GamePlayer> {
    GamePlayer findPlayerGameContribution(int gameId, String playerId);
}
