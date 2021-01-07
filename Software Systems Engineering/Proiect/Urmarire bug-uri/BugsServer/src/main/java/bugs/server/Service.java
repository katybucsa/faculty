package bugs.server;


import bugs.model.Bug;
import bugs.model.Stage;
import bugs.model.User;
import bugs.persistance.BugRepository;
import bugs.persistance.RepositoryException;
import bugs.persistance.UserRepository;
import bugs.services.AppException;
import bugs.services.IObserver;
import bugs.services.IService;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {
    private UserRepository userRepo;
    private BugRepository bugRepo;
    private Map<String, IObserver> loggedUsers;

    public Service(UserRepository userRepo, BugRepository bugRepo) {
        this.userRepo = userRepo;
        this.bugRepo = bugRepo;
        loggedUsers = new ConcurrentHashMap<>();
    }

    public synchronized User login(User user, IObserver o) {
        try {
            User u = userRepo.findOne(user);
            if (user == null)
                throw new AppException("Date invalide!");
            loggedUsers.put(u.getID(), o);
            return u;
        } catch (RepositoryException ex) {
            throw new AppException(ex.getMessage());
        }
    }

    public Iterable<User> findAllUsers() {
        return userRepo.findAll();
    }

    public Iterable<Bug> findAllBugsForProg() {
        return bugRepo.findAllForProgrammer();
    }

    public Iterable<Bug> findAllBugsForTester() {
        return bugRepo.findAllForTester();
    }

    @Override
    public Iterable<Bug> findAllSolvedBugs() {
        return bugRepo.findAllSolvedBugs();
    }

    public synchronized void repairBug(Bug bug) {
        bug.setStage(Stage.Reparat);
        bugRepo.update(bug);
        notifyObservers();
    }

    public synchronized void saveBug(Bug bug) {
        bugRepo.save(bug);
        notifyObservers();
    }

    @Override
    public void addUser(User user) {
        try {
            userRepo.save(user);
            notifyObservers();
        } catch (RepositoryException re) {
            throw new AppException(re.getMessage());
        }
    }

    @Override
    public void updateUser(User newUser) {
        try {
            userRepo.update(newUser);
            notifyObservers();
        } catch (RepositoryException re) {
            throw new AppException(re.getMessage());
        }
    }

    @Override
    public void deleteUser(String userId) {
        try {
            if (loggedUsers.containsKey(userId))
                throw new AppException("Utilizatorul nu poate fi sters deoarece este logat in sistem!");
            userRepo.delete(userId);
            notifyObservers();
        } catch (RepositoryException re) {
            throw new AppException(re.getMessage());
        }
    }

    @Override
    public List<String> getProgrammersList() {
        List<String> programmers = new ArrayList<>();
        userRepo.findAllProgrammers().forEach(x -> programmers.add(x.getUsername()));
        return programmers;
    }

    @Override
    public void asignBug(String prog, Bug bug) {
        User user = userRepo.findOne(prog);
//        bug.setResponsibleProgrammer(user);
        Set<Bug> toRes = new HashSet<>();
        toRes.add(bug);
        user.setBugsToResolve(toRes);
//        bugRepo.update(bug);
        userRepo.update(user);
        notifyObservers();
    }

    @Override
    public void updateBug(Bug bug) {
        bugRepo.update(bug);
        notifyObservers();
    }

    public void notifyObservers() {
        ExecutorService executor = Executors.newFixedThreadPool(loggedUsers.size());
        loggedUsers.values().forEach(x -> executor.execute(() -> {
            System.out.println("Notifying observer");
            try {
                x.update();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }));
        executor.shutdown();
    }
}
