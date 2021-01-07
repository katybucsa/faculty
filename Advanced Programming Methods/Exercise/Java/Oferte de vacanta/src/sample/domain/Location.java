package sample.domain;

import sample.repository.HasID;

public class Location implements HasID<String> {
    private String locationId;
    private String locationName;
    private String hotel;
    private Integer noRooms;
    private double pricePerNight;

    public Location(String locationId, String locationName, String hotel, Integer noRooms, double pricePerNight) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.hotel = hotel;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
    }

    public String getID() {
        return locationId;
    }

    public void setID(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Integer getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(Integer noRooms) {
        this.noRooms = noRooms;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId='" + locationId + '\'' +
                ", locationName='" + locationName + '\'' +
                ", hotel='" + hotel + '\'' +
                ", noRooms=" + noRooms +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
