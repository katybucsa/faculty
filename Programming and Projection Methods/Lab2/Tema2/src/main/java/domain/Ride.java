package domain;



import org.springframework.util.StringUtils;
import repository.HasID;

public class Ride implements HasID<Integer> {
    private int ride_id;
    private String destination;
    private String date;
    private String hour;
    private String places;

    public Ride(int ride_id,String destination, String date, String hour) {
        this.ride_id=ride_id;
        this.destination = destination;
        this.date= date;
        this.hour=hour;
        places="0000000000000000000";
    }

    @Override
    public Integer getID() {
        return ride_id;
    }

    @Override
    public void setID(Integer s) {
        ride_id=s;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getPlaces() {
        return places;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date= date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public int getNrPlacesAvailable(){
        return StringUtils.countOccurrencesOf(places,"0")-1;
    }

}
