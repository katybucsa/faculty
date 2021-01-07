package company.model;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ride")
public class Ride implements Serializable, HasID<Integer> {
    @Id
    @GeneratedValue
    private int ride_id;
    private String destination;
    private String date;
    private String hour;
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

    public void initiatePlaces() {
        this.places = "0000000000000000000";
    }

    public String toString() {
        return ride_id + " " + destination + " " + date + " " + hour;
    }

}
