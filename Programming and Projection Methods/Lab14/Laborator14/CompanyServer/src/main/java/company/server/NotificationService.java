package company.server;

import company.model.Notification;
import company.model.NotificationType;
import company.services.INotificationService;
import org.springframework.jms.core.JmsOperations;

/**
 * Created by: Katy Bucșa
 * Date and time: 05/31/2019, 17:38
 */

public class NotificationService implements INotificationService {
    private JmsOperations jmsOperations;

    public NotificationService(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    @Override
    public void placesBooked() {
        System.out.println("Booked places notification ...");
        Notification notification = new Notification(NotificationType.BOOKED_PLACES);
        jmsOperations.convertAndSend(notification);
        System.out.println("Send booked places notification to ActiveMq ...");

    }
}
