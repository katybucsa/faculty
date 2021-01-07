package game.persistance;


import game.model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class PlayerRepository implements IPlayerRepository {
    private static final Logger logger = LogManager.getLogger();
    private SessionFactory sFactory;
    private Validator<Player> valid;

    public PlayerRepository(SessionFactory sFactory, PlayerValidator valid) {
        logger.info("Initializing PlayerRepository with session factory: {}", sFactory);
        this.sFactory = sFactory;
        this.valid = valid;
    }

    @Override
    public Player findOne(String usrname) {
        logger.traceEntry("Finding Player with username {}", usrname);
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query q = session.createQuery("from Player where username=:usrname", Player.class);
                q.setParameter("usrname", usrname);
                List<Player> l = q.getResultList();
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
    public Player findOne(Player player) {
        Player u = findOne(player.getId());
        if (u == null)
            return null;
        if (player.getPassword().equals(u.getPassword()))
            return u;
        return null;
    }

    @Override
    public void save(Player user) throws ValidationException {

    }

    @Override
    public void update(Player user) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Player> findAll() {
        return null;
    }

    @Override
    public void delete(String s) {

    }
}
