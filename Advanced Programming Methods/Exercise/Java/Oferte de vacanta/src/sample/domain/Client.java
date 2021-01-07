package sample.domain;

import sample.repository.HasID;

import java.util.HashMap;

public class Client implements HasID<Long> {
    private Long clientId;
    private String name;

    public Client(Long clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public Long getID() {
        return clientId;
    }

    public void setID(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                '}';
    }
}
