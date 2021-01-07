package company.services;

public interface INotificationReceiver {
    void start(INotificationSubscriber subscriber);

    void stop();
}
