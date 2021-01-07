package sample.domain;

import javafx.util.Pair;
import sample.repository.HasID;

import java.time.LocalDateTime;

public class Reservation implements HasID<Pair<Client,Location>> {
    private Client client;
    private Location location;
    private LocalDateTime startDate;
    private Integer noNights;

    public Reservation(Client client, Location location, LocalDateTime startDate, Integer noNights) {
        this.client = client;
        this.location = location;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    public Pair<Client,Location> getID(){
        return new Pair<>(client,location);
    }
    public void setID(Pair<Client,Location> pair){
        this.client=pair.getKey();
        this.location=pair.getValue();
    }

    public String getLocationName(){
        return location.getLocationName();
    }

    public String getLocationHotel(){
        return location.getHotel();
    }

    public Double getLocationPrice(){
        return location.getPricePerNight();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getNoNights() {
        return noNights;
    }

    public void setNoNights(Integer noNights) {
        this.noNights = noNights;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "client=" + client +
                ", location=" + location +
                ", startDate=" + startDate +
                ", noNights=" + noNights +
                '}';
    }
}
