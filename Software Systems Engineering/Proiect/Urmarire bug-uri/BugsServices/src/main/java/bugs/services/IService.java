package bugs.services;


import bugs.model.Bug;
import bugs.model.User;

import java.util.List;

public interface IService {
    User login(User user, IObserver o);

    Iterable<User> findAllUsers();

    Iterable<Bug> findAllBugsForProg();

    Iterable<Bug> findAllBugsForTester();

    Iterable<Bug> findAllSolvedBugs();

    void repairBug(Bug bug);

    void saveBug(Bug bug);

    void addUser(User user);

    void updateUser(User newUser);

    void deleteUser(String userId);

    List<String> getProgrammersList();

    void asignBug(String prog, Bug bug);

    void updateBug(Bug bug);
}
