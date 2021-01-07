package sample.domain;

import javafx.util.Pair;
import sample.repository.HasID;


import java.time.LocalDateTime;
import java.time.Month;

public class Vanzare implements HasID<Pair<Client,Laptop>> {
    private Client client;
    private Laptop laptop;
    private LocalDateTime date;


    public Vanzare(Client client, Laptop laptop, LocalDateTime date) {
        this.client = client;
        this.laptop = laptop;
        this.date = date;
    }

    public float getPret(){
        return laptop.getPret();
    }

    public Month getMonth(){
        return date.getMonth();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public LocalDateTime getLocalDateTime() {
        return date;
    }

    public void setLocalDateTime(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vanzare{" +
                "client=" + client +
                ", laptop=" + laptop +
                ", date=" + date +
                '}';
    }

    @Override
    public Pair<Client, Laptop> getID() {
        return new Pair<>(client,laptop);
    }

    @Override
    public void setID(Pair<Client, Laptop> clientLaptopPair) {
        this.client=clientLaptopPair.getKey();
        this.laptop=clientLaptopPair.getValue();
    }
}
