package company.persistance;



import company.model.Ride;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RideRepository implements IRideRepository {
    private static final Logger logger = LogManager.getLogger();
    private DBConnection dbConnection;
    private Validator<Ride> valid;

    public RideRepository(Properties properties, RideValidator v) {
        logger.info("Initializing RideRepository with properties: {}", properties);
        dbConnection = new DBConnection(properties);
        this.valid = v;
    }

    @Override
    public Ride findOne(Integer id) {
        logger.traceEntry("Finding ride with id {}", id);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("select * from Ride where ride_id=?")) {
            stat.setInt(1, id);
            try (ResultSet resultSet = stat.executeQuery()) {
                resultSet.next();
                String destination = resultSet.getString("destination");
                String date = resultSet.getString("date");
                String hour = resultSet.getString("hour");
                String places = resultSet.getString("places");
                Ride r = new Ride(id,destination, date, hour);
                r.setPlaces(places);
                return r;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Ride couldn't be found in the database!");
        }
    }


    @Override
    public void save(Ride ride) {
        logger.traceEntry("Saving ride {} ", ride);
        valid.validate(ride);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("INSERT INTO Ride (ride_id,destination,date,hour,places) VALUES (?,?,?,?,?)")) {
            stat.setInt(1,ride.getID());
            stat.setString(2, ride.getDestination());
            stat.setString(3, ride.getDate());
            stat.setString(4, ride.getHour());
            stat.setString(5, ride.getPlaces());
            stat.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Database error " + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Ride c) {
        logger.traceEntry("updating ride with {}", c);
        valid.validate(c);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("UPDATE Ride SET places=? WHERE ride_id=?")) {
            stat.setString(1, c.getPlaces());
            stat.setInt(2,c.getID());
            stat.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Database error " + e);
        }
        logger.traceExit();
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement prepSt = conn.prepareStatement("SELECT COUNT(*) AS [SIZE] FROM Ride")) {
            try (ResultSet resultSet = prepSt.executeQuery()) {
                if (resultSet.next()) {
                    logger.traceExit(resultSet.getInt("SIZE"));
                    return resultSet.getInt("SIZE");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Database error " + e);
        }
        return 0;
    }

    @Override
    public Iterable<Ride> findAll() {
        logger.traceEntry();
        Connection conn = dbConnection.getConnection();
        List<Ride> courses = new ArrayList<>();
        try (PreparedStatement stat = conn.prepareStatement("select * from Ride")) {
            try (ResultSet resultSet = stat.executeQuery()) {
                while (resultSet.next()) {
                    Integer id =resultSet.getInt("ride_id");
                    String destination = resultSet.getString("destination");
                    String date = resultSet.getString("date");
                    String hour = resultSet.getString("hour");
                    String places = resultSet.getString("places");
                    Ride v = new Ride(id,destination, date, hour);
                    v.setPlaces(places);
                    courses.add(v);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Database error " + e);
        }
        logger.traceExit(courses);
        return courses;
    }

    @Override
    public void delete(Integer id) {
        logger.traceEntry("deleting ride with {}", id);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("DELETE FROM Ride WHERE ride_id=? ")) {
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Database error " + e);
        }
        logger.traceExit();
    }

    @Override
    public Ride findOneby_Destination_Date_Hour(String destination, String date, String hour) {
        logger.traceEntry("Finding ride with destination, date and hour {}",destination,date,hour);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("select * from Ride where destination=? and date=? and hour=?")) {
            stat.setString(1, destination);
            stat.setString(2, date);
            stat.setString(3, hour);
            try (ResultSet resultSet = stat.executeQuery()) {
                resultSet.next();
                Integer id=resultSet.getInt("ride_id");
                String places = resultSet.getString("places");
                Ride r = new Ride(id,destination, date, hour);
                r.setPlaces(places);
                return r;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Ride couldn't be found in the database!");
        }
    }
}
