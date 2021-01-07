package bugs.persistance;

import bugs.model.Bug;
import bugs.model.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class BugRepository implements IBugRepository {
    private static final Logger logger = LogManager.getLogger();
    private SessionFactory sFactory;
    private Validator<Bug> valid;

    public BugRepository(SessionFactory sf, BugValidator valid) {
        this.sFactory = sf;
        this.valid = valid;
    }

    @Override
    public Bug findOne(Integer integer) {
        return null;
    }

    @Override
    public void save(Bug bug) throws ValidationException {
        logger.traceEntry("Saving bug {} ", bug);
        valid.validate(bug);
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(bug);
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public void update(Bug bug) {
        logger.traceEntry("updating bug {}", bug);
        valid.validate(bug);
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Bug b = session.load(Bug.class, bug.getID());
                b.setStage(bug.getStage());
//                b.setResponsibleProgrammer(bug.getResponsibleProgrammer());
//                b.setSolvedBy(bug.getSolvedBy());
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Bug> findAll() {
        return null;
    }

    @Override
    public Iterable<Bug> findAllForProgrammer() {
        logger.traceEntry();
        List<Bug> bugs = new ArrayList<>();
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query q = session.createQuery("from Bug where stage in (:nou,:red)", Bug.class);
                q.setParameter("nou", Stage.Nou);
                q.setParameter("red", Stage.Redeschis);
                bugs = q.getResultList();
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
            logger.traceExit(bugs);
            return bugs;
        }
    }

    @Override
    public Iterable<Bug> findAllForTester() {
        logger.traceEntry();
        List<Bug> bugs = new ArrayList<>();
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query q = session.createQuery("from Bug where stage in (:st1,:st2,:st3)", Bug.class);
                q.setParameter("st1", Stage.Nou);
                q.setParameter("st2", Stage.Redeschis);
                q.setParameter("st3", Stage.Inchis);
                bugs = q.getResultList();
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
            logger.traceExit(bugs);
            return bugs;
        }
    }


    @Override
    public Iterable<Bug> findAllSolvedBugs() {
        logger.traceEntry();
        List<Bug> solvedBugs = new ArrayList<>();
        try (Session session = sFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query q = session.createQuery("from Bug where stage=:st", Bug.class);
                q.setParameter("st", Stage.Reparat);
                solvedBugs = q.getResultList();
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
            logger.traceExit(solvedBugs);
            return solvedBugs;
        }
    }


    @Override
    public void delete(Integer integer) {

    }

}
