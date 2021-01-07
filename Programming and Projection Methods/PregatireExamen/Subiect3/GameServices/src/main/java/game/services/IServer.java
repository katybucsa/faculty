package game.services;


import game.model.Player;

import java.rmi.RemoteException;

public interface IServer {
    void login(Player player, IObserver o);

    void logout(Player player, IObserver o);

    void startGame(Player player, int poz);

//    RBooking[] findAllRBookings(String dest, String date, String hour);
//
//    String bookPlaces(Ride ride, Client client, int nrplaces);
//
//    Ride[] findAllRides();

    void notifyObservers(Notification notification, String player) throws RemoteException;

    void attack(String gameIdentifier, Player player, int poz);
}
