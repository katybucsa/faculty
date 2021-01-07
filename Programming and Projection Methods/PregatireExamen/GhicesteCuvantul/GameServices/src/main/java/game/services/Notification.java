package game.services;

import java.io.Serializable;
import java.util.List;

/**
 * Created on: 21-Jun-19, 13:08
 *
 * @author: Katy Bucșa
 */

public class Notification implements Serializable {
    private Type type;
    private List<String> participants;
    public Notification() {
    }

    public Notification(Type type) {
        this.type = type;
    }

    public Notification(Type type, List<String> participants) {
        this.type = type;
        this.participants = participants;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}
