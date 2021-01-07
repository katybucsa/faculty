package bugs.persistance;


import bugs.model.Role;
import bugs.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;

public class UserRepository implements IUserRepository {
    private static final Logger logger = LogManager.getLogger();
    private SessionFactory sFactory;
    private Validator<User> valid;

    public UserRepository(SessionFactory f, UserValidator valid) {
        this.sFactory = f;
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
            throw new RepositoryException("Username invalid!\n");
        if (user.getPassword().equals(u.getPassword()))
            return u;
        throw new RepositoryException("Parola invalida!\n");
    }

    @Override
    public void update(User user) {
        logger.traceEntry("Updating user with username {} ", user.getID());
        valid.validate(user);
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User u = session.load(User.class, user.getID());
                u.setPassword(user.getPassword());
                u.setRole(user.getRole());
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setBugsToResolve(user.getBugsToResolve());
                u.setSolvedBugs(user.getSolvedBugs());
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
                throw new RepositoryException(ex.getMessage());
            }
        }
    }

    @Override
    public void save(User user) {
        logger.traceEntry("Saving user {} ", user);
        valid.validate(user);
        try (Session session = sFactory.openSession()) {
            Transaction tran = null;
            try {
                tran = session.beginTransaction();
                session.save(user);
                tran.commit();
            } catch (RuntimeException ex) {
                if (tran != null)
                    tran.rollback();
                throw new RepositoryException(ex.getMessage());
            }
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<User> findAll() {

        logger.traceEntry();
        List<User> users = new ArrayList<>();
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                users = session.createQuery("from User", User.class).list();
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        logger.traceExit(users);
        return users;
    }

    @Override
    public void delete(String s) {
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                User delUser = session.createQuery("from User where username=:id", User.class)
                        .setParameter("id", s)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(delUser);
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
                throw new RepositoryException(ex.getMessage());
            }
        }
    }

    @Override
    public Iterable<User> findAllProgrammers() {

        logger.traceEntry();
        List<User> programmers = new ArrayList<>();
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                programmers = session.createQuery("from User where role=:role", User.class)
                        .setParameter("role", Role.Programator)
                        .list();
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        logger.traceExit(programmers);
        return programmers;
    }
}
