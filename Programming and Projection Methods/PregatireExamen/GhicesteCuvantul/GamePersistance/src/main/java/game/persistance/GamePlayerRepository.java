package game.persistance;

import game.model.GamePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.util.List;

/**
 * Created on: 23-Jun-19, 09:29
 *
 * @author: Katy Bucșa
 */

public class GamePlayerRepository implements IGamePlayerRepository {
    private static final Logger logger = LogManager.getLogger();


    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public GamePlayerRepository(SessionFactory sessionFactory) {
        logger.info("Initializing GamePlayerRepository with session factory: {}", sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public GamePlayerRepository() {

    }

    @Override
    public GamePlayer findOne(Integer integer) {
        return null;
    }

    @Override
    public void save(GamePlayer gamePlayer) throws ValidationException {
        logger.traceEntry("Saving gamePlayer {} ", gamePlayer);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(gamePlayer);
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
        }

    }

    @Override
    public void update(GamePlayer gamePlayer) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<GamePlayer> findAll() {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public GamePlayer findPlayerGameContribution(int gameId, String playerId) {
        logger.traceEntry("Finding GamePlayer for game {}  and player {}",gameId,playerId);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query q = session.createQuery("from GamePlayer where game=:g and player=:p", GamePlayer.class);
                q.setParameter("g",gameId);
                q.setParameter("p",playerId);
                List<GamePlayer> l = q.getResultList();
                transaction.commit();
                return l.get(0);
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return null;
    }
}
