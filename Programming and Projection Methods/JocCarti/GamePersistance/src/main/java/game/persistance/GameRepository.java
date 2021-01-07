package game.persistance;


import game.model.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.util.List;


public class GameRepository implements IGameRepository {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private Validator<Game> gameValid;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public GameRepository(SessionFactory sessionFactory, GameValidator v) {
        logger.info("Initializing GameRepository with session factory: {}", sessionFactory);
        this.gameValid = v;
        this.sessionFactory = sessionFactory;
    }

    public GameRepository() {

    }

    @Override
    public Game findOne(Integer id) {
        return null;
    }


    @Override
    public void save(Game game) {
        logger.traceEntry("Saving game {} ", game);
        gameValid.validate(game);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(game);
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public void update(Game game) {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Game> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Game findLastAdded() {
        logger.traceEntry("Finding last added game");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query q = session.createQuery("from Game where id=(select max(id) from Game)", Game.class);
                List<Game> l = q.getResultList();
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
