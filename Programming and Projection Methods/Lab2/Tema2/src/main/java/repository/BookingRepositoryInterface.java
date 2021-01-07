package repository;

import domain.Booking;
import domain.Client;
import domain.Ride;
import javafx.util.Pair;
import utils.RBooking;

public interface BookingRepositoryInterface extends RepositoryInterface<Pair<Client, Ride>, Booking> {
    Iterable<RBooking> findBookingsByDestDateHour(String destination, String date, String hour);
}
