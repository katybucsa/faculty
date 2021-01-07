package game.services;


import game.model.Player;

import java.rmi.RemoteException;

public interface IServer {
    void login(Player player, IObserver o);

    void logout(Player player, IObserver o);

    void startGame(Player player);

    void notifyObserver(Notification notification, String player) throws RemoteException;

    void attack(String gameIdentifier, Player player, String carte);
}
