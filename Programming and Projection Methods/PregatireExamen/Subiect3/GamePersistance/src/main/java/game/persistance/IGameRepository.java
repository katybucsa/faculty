package game.persistance;


import game.model.Game;

import java.util.List;


public interface IGameRepository extends IRepository<Integer, Game> {
    List<Game> findAllPlayerGames(String id);
}