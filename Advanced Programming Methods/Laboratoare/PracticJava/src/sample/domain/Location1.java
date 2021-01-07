package sample.domain;

public class Location1 {
    private String locationName;
    private String locationHotel;
    private Integer nrNopti;

    public Location1(String locationName, String locationHotel, Integer nrNopti) {
        this.locationName = locationName;
        this.locationHotel = locationHotel;
        this.nrNopti = nrNopti;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationHotel() {
        return locationHotel;
    }

    public void setLocationHotel(String locationHotel) {
        this.locationHotel = locationHotel;
    }

    public Integer getNrNopti() {
        return nrNopti;
    }

    public void setNrNopti(Integer nrNopti) {
        this.nrNopti = nrNopti;
    }
}
