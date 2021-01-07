package bugs.client;


import bugs.model.Bug;
import bugs.model.Role;
import bugs.model.Stage;
import bugs.model.User;
import bugs.services.AppException;
import bugs.services.IObserver;
import bugs.services.IService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class Controller extends UnicastRemoteObject implements IObserver, Serializable {
    private IService service;
    private ObservableList<User> userModel;
    private ObservableList<Bug> bugProgModel;
    private ObservableList<Bug> bugTesterModel;
    private ObservableList<Bug> sbModel;

    public Controller(IService service) throws RemoteException {
        this.service = service;
        this.userModel = FXCollections.observableArrayList();
        this.bugProgModel = FXCollections.observableArrayList();
        this.bugTesterModel = FXCollections.observableArrayList();
        this.sbModel = FXCollections.observableArrayList();
    }

    private void populateUserList() {
        userModel.clear();
        service.findAllUsers().forEach(x -> userModel.add(x));
    }

    public ObservableList<User> getUserModel() {
        populateUserList();
        return userModel;
    }

    private void populateBugProgList() {
        bugProgModel.clear();
        service.findAllBugsForProg().forEach(x -> bugProgModel.add(x));
    }

    public ObservableList<Bug> getBugProgModel() {
        populateBugProgList();
        return bugProgModel;
    }

    private void populateBugTesterList() {
        bugTesterModel.clear();
        service.findAllBugsForTester().forEach(x -> bugTesterModel.add(x));
    }

    public ObservableList<Bug> getBugTesterModel() {
        populateBugTesterList();
        return bugTesterModel;
    }

    public ObservableList<Bug> getSBModel() {
        populateSBModel();
        return sbModel;
    }

    private void populateSBModel() {
        sbModel.clear();
        service.findAllSolvedBugs().forEach(x -> sbModel.add(x));
    }

    public User login(String username, String password) {
        return service.login(new User(username, password), this);
    }

    public void repairBug(Bug bug) {
        service.repairBug(bug);
    }

    public void addBug(String naming, String description) {
        Bug bug = new Bug(naming, description, Stage.Nou);
        service.saveBug(bug);
    }


    public void addUser(String username, String pass, String firstName, String lastName, Role role) {
        User user = new User(username, pass, role, firstName, lastName);
        service.addUser(user);
    }

    public void updateUser(String username, String password, Role role, String fName, String lName) {
        User newUser = new User(username, password, role, fName, lName);
        service.updateUser(newUser);
    }

    public void deleteUser(String userId) {
        service.deleteUser(userId);
    }

    public List<String> getProgrammersList() {
        return service.getProgrammersList();
    }

    public void asignBug(String prog, Bug bug) {
        service.asignBug(prog, bug);
    }


    public boolean testCodeSolvedBug(Bug bug) {
        boolean solved;
        if (Math.random() < 0.5) {
            bug.setStage(Stage.Inchis);
            solved = true;
        } else {
            bug.setStage(Stage.Redeschis);
            bug.setSolvedBy(null);
            solved = false;
        }
        service.updateBug(bug);
        return solved;
    }

    @Override
    public void update() throws AppException, RemoteException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                populateBugProgList();
                populateBugTesterList();
                populateUserList();
                populateSBModel();
            }
        });
    }
}
