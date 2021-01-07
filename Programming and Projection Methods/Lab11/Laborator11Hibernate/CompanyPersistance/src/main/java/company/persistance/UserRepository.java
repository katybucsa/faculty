package company.persistance;

import company.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;
import java.util.Properties;


public class UserRepository implements IUserRepository {
    private static final Logger logger = LogManager.getLogger();
    private DBConnection dbConnection;
    private SessionFactory sFactory;
    private Validator<User> valid;

    public UserRepository(Properties properties, SessionFactory s, UserValidator valid) {
        logger.info("Initializing UserRepository with properties: {}", properties);
        this.dbConnection = new DBConnection(properties);
        this.sFactory = s;
        this.valid = valid;
    }

    @Override
    public User findOne(String usrname) {
        logger.traceEntry("Finding User with id {}", usrname);
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query q = session.createQuery("from User where username=:usrname", User.class);
                q.setParameter("usrname", usrname);
                List<User> l = q.getResultList();
                transaction.commit();
                return l.get(0);
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public User findOne(User user) {
        User u = findOne(user.getID());
        if (u == null)
            return null;
        if (user.getPassword().equals(u.getPassword()))
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
