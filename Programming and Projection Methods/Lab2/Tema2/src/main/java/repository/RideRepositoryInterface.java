package repository;

import domain.Ride;

public interface RideRepositoryInterface extends RepositoryInterface<Integer, Ride> {
    Ride findOneby_Destination_Date_Hour(String destination,String date, String hour);
}
