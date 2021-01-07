package company.services;

import company.model.Notification;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INotificationSubscriber  extends Remote {
    void notificationReceived(Notification notification) throws RemoteException;
}
