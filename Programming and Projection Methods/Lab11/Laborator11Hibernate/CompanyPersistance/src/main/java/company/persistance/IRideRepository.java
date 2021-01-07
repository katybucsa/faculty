package company.persistance;


import company.model.Ride;

public interface IRideRepository extends IRepository<Integer, Ride> {
    Ride findOneby_Destination_Date_Hour(String destination, String date, String hour);
}
