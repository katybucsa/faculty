package company.persistance;

import company.model.*;

public interface IBookingRepository extends IRepository<Pair<Clientj, Ride>, Booking> {
    Iterable<RBooking> findBookingsByDestDateHour(String destination, String date, String hour);
}
