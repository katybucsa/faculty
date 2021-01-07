package company.services;

import company.model.Client;
import company.model.RBooking;
import company.model.Ride;
import company.model.User;

public interface IServer {
    void login(User u, INotificationSubscriber o);

    void logout(User u, INotificationSubscriber o);

    RBooking[] findAllRBookings(String dest, String date, String hour);

    String bookPlaces(Ride ride, Client client, int nrplaces);

    Ride[] findAllRides();

    //void notifyObservers() throws RemoteException;
}
