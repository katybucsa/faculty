package company.persistance;

import company.model.*;

public interface IBookingRepository extends IRepository<Pair<Client, Ride>, Booking> {
    Iterable<RBooking> findBookingsByDestDateHour(String destination, String date, String hour);
}
