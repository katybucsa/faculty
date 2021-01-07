package company.model;


/**
 * Created by: Katy Bucșa
 * Date and time: 05/31/2019, 18:04
 */

public class Notification {
    private NotificationType type;

    public Notification() {
    }


    public Notification(NotificationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Notification{ " +
                "type=" + type +
                '}';
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
