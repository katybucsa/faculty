package company.model;


import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Ride")
public class Ride implements Serializable, HasID<Integer> {
    @Id
    @Column(name="ride_id")
    private int ride_id;

    @Column(name="destination")
    private String destination;

    @Column(name = "date")
    private String date;

    @Column(name = "hour")
    private String hour;

    @Column(name = "places")
    private String places;

    public Ride(int ride_id, String destination, String date, String hour) {
        this.ride_id = ride_id;
        this.destination = destination;
        this.date = date;
        this.hour = hour;
        places = "0000000000000000000";
    }
    public Ride() {
    }

    public int getRide_id() {
        return ride_id;
    }

    public void setRide_id(int ride_id) {
        this.ride_id = ride_id;
    }

    @Override
    public Integer getID() {
        return ride_id;
    }

    @Override
    public void setID(Integer s) {
        ride_id = s;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public int getNrPlacesAvailable() {
        return StringUtils.countOccurrencesOf(places, "0") - 1;
    }

}
