package company.persistance;

import company.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserRepository implements IUserRepository {
    private static final Logger logger = LogManager.getLogger();
    private DBConnection dbConnection;
    private Validator<User> valid;

    public UserRepository(Properties properties, UserValidator valid) {
        logger.info("Initializing UserRepository with properties: {}", properties);
        this.dbConnection = new DBConnection(properties);
        this.valid = valid;
    }

    @Override
    public User findOne(String usrname) {
        logger.traceEntry("Finding User with id {}", usrname);
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("select * from User where username=?")) {
            stat.setString(1, usrname);
            try (ResultSet resultSet = stat.executeQuery()) {
                resultSet.next();
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User u = new User(username, password);
                return u;
            }
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public User findOne(User user) {
        User u=findOne(user.getID());
        if(u==null)
            return null;
        if(user.getPassword().equals(u.getPassword()))
            return u;
        return null;
    }

    @Override
    public void save(User user) throws ValidationException {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public void delete(String s) {

    }
}
