package game.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote {
    void update(Notification notification) throws AppException, RemoteException;
}
