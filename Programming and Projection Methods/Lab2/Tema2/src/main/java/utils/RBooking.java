package utils;

public class RBooking {
    private String name;
    private int place;

    public RBooking(String name, int place){
        this.name=name;
        this.place=place;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getPlace(){
        return place;
    }

    public void setPlace(int place){
        this.place=place;
    }
}
