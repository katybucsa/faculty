package company.persistance;


import company.model.Clientj;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ClientRepository implements IClientRepository {
    private DBConnection dbConnection;
    private static final Logger logger = LogManager.getLogger();
    private Validator<Clientj> valid;

    public ClientRepository(Properties properties, ClientValidator v) {
        logger.info("Initializing ClientRepository with properties: {}", properties);
        dbConnection = new DBConnection(properties);
        this.valid = v;
    }

    @Override
    public Clientj findOne(Integer integer) {
        return null;
    }

    public Clientj findOne(String name) {
        logger.traceEntry("Finding client with name {}", name);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("select * from Client where name=?")) {
            stat.setString(1, name);
            try (ResultSet resultSet = stat.executeQuery()) {
                resultSet.next();
                int id = resultSet.getInt("client_id");
                Clientj c = new Clientj(id, name);
                return c;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Ride couldn't be found in the database!");
        }
    }

    @Override
    public void save(Clientj client) throws ValidationException {
        logger.traceEntry("Saving client {} ", client);
        valid.validate(client);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("INSERT INTO Client (name) VALUES (?)")) {
            stat.setString(1, client.getName());
            stat.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Database error " + e);
        }
        logger.traceExit();
    }

    public Clientj findLastAdded() {
        logger.traceEntry("Finding last client added");
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("select max(client_id) as [id],name from Client")) {
            try (ResultSet resultSet = stat.executeQuery()) {
                resultSet.next();
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Clientj c = new Clientj(id, name);
                return c;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Client couldn't be found in the database!");
        }
    }

    @Override
    public void update(Clientj client) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Clientj> findAll() {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
