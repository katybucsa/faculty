package company.persistance;

import company.model.Booking;
import company.model.Client;
import company.model.RBooking;
import company.model.Ride;
import javafx.util.Pair;

public interface IBookingRepository extends IRepository<Pair<Client, Ride>, Booking> {
    Iterable<RBooking> findBookingsByDestDateHour(String destination, String date, String hour);
}
