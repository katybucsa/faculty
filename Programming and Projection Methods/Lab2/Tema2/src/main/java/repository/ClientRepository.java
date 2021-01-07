package repository;

import domain.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ClientRepository implements ClientRepositoryInterface {
    private DBConnection dbConnection;
    private static final Logger logger= LogManager.getLogger();
    private  Validator<Client> valid;

    public ClientRepository(Properties properties, ClientValidator v){
        logger.info("Initializing ClientRepository with properties: {}",properties);
        dbConnection=new DBConnection(properties);
        this.valid=v;
    }

    @Override
    public Client findOne(Integer integer) {
        return null;
    }

    public Client findOne(String name){
        logger.traceEntry("Finding client with name {}", name);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("select * from Client where name=?")) {
            stat.setString(1, name);
            try (ResultSet resultSet = stat.executeQuery()) {
                resultSet.next();
                int id = resultSet.getInt("client_id");
                Client c=new Client(id,name);
                return c;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Ride couldn't be found in the database!");
        }
    }

    @Override
    public void save(Client client) throws ValidationException {
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

    public Client findLastAdded(){
        logger.traceEntry("Finding last client added");
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("select max(client_id) as [id],name from Client")) {
            try (ResultSet resultSet = stat.executeQuery()) {
                resultSet.next();
                Integer id= resultSet.getInt("id");
                String name = resultSet.getString("name");
                Client c=new Client(id,name);
                return c;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException("Client couldn't be found in the database!");
        }
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Client> findAll() {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
