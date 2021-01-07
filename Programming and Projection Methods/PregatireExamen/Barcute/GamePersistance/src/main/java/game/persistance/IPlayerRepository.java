package game.persistance;


import game.model.Player;

public interface IPlayerRepository extends IRepository<String, Player> {
    Player findOne(Player user);
}
