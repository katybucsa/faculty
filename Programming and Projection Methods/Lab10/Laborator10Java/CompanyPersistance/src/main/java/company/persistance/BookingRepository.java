package company.persistance;


import company.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class BookingRepository implements IBookingRepository {
    private static final Logger logger = LogManager.getLogger();
    private DBConnection dbConnection;
    private Validator<Booking> valid;
    private IRideRepository riderepo;
    private IClientRepository clientrepo;

    public BookingRepository(Properties properties, Validator<Booking> valid, RideRepository riderepo, ClientRepository clientrepo) {
        this.riderepo = riderepo;
        this.clientrepo = clientrepo;
        logger.info("Initializing BookingRepository with properties: {}", properties);
        dbConnection = new DBConnection(properties);
        this.valid = valid;
    }

    @Override
    public Iterable<RBooking> findBookingsByDestDateHour(String destination, String date, String hour) {
        logger.traceEntry("Finding bookings for a destination, date and hour", destination, date, hour);
        Connection conn = dbConnection.getConnection();
        List<RBooking> rbookings = new ArrayList<>();
        try (PreparedStatement stat = conn.prepareStatement("select c.name as [name], b.places as [places] " +
                "from Ride as [r] inner join Booking as [b] on r.ride_id=b.ride_id  inner join Client as [c] on b.client_id=c.client_id " +
                "where r.destination=? and r.date=? and r.hour=?")) {
            stat.setString(1, destination);
            stat.setString(2, date);
            stat.setString(3, hour);
            try (ResultSet resultSet = stat.executeQuery()) {
                while (resultSet.next()) {
                    String cname = resultSet.getString("name");
                    String cplaces = resultSet.getString("places");
                    String[] cp = cplaces.split(",");
                    for (int i = 0; i < cp.length; i++) {
                        RBooking rb = new RBooking(cname, Integer.parseInt(cp[i]));
                        rbookings.add(rb);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Ride r = riderepo.findOneby_Destination_Date_Hour(destination, date, hour);
        for (int i = 1; i < 19; ++i) {
            if (r.getPlaces().charAt(i) == '0')
                rbookings.add(new RBooking("-", i));
        }
        rbookings.sort(Comparator.comparingInt(RBooking::getPlace));
        return rbookings;
    }


    @Override
    public Booking findOne(Pair<Clientj, Ride> clientRidePair) {
        return null;
    }

    @Override
    public void save(Booking booking) {
        logger.traceEntry("Saving booking {} ", booking);
        valid.validate(booking);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("INSERT INTO Booking (ride_id,client_id,number_of_places_wanted,places) VALUES (?,?,?,?)")) {
            stat.setInt(1, booking.getRide().getID());
            stat.setInt(2, booking.getClient().getID());
            stat.setInt(3, booking.getNr_places_wanted());
            stat.setString(4, booking.getPlaces());
            stat.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Database error " + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Booking booking) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Booking> findAll() {
        return null;
    }

    @Override
    public void delete(Pair<Clientj, Ride> clientRidePair) {

    }
}
