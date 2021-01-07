package domain;

import javafx.util.Pair;
import repository.HasID;

public class Booking implements HasID<Pair<Client,Ride>> {
    private Client client;
    private Ride ride;
    private int nr_places_wanted;
    private String places;

    public Client getClient() {
        return client;
    }

    public Ride getRide() {
        return ride;
    }

    public Booking(Client client, Ride ride, int places_wanted, String places){
        this.client=client;
        this.ride=ride;
        this.nr_places_wanted=places_wanted;
        this.places=places;
    }

    @Override
    public Pair<Client,Ride> getID() {
        return new Pair<>(client,ride);
    }

    @Override
    public void setID(Pair<Client, Ride> clientRidePair) {
        this.client=clientRidePair.getKey();
        this.ride=clientRidePair.getValue();
    }

    public int getNr_places_wanted(){
        return nr_places_wanted;
    }

    public void setNr_places_wanted(int nr){
        this.nr_places_wanted=nr;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }
}
