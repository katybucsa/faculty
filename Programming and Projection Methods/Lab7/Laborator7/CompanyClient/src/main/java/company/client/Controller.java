package company.client;

import company.model.Client;
import company.model.RBooking;

import company.model.Ride;
import company.model.User;
import company.services.IObserver;
import company.services.IServer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Controller implements IObserver {
    private IServer server;
    private User user;
    private ObservableList<Ride> rModel;
    private List<Pair<ObservableList<RBooking>, List<String>>> rbModelList;

    public Controller(IServer serv) {
        this.server = serv;
        rModel = FXCollections.observableArrayList();
        rbModelList = new ArrayList<>();
    }

    private void populateRideList() {
        rModel.clear();
        Ride[] rides = server.findAllRides();
        for (Ride r : rides) {
            rModel.add(r);
        }
    }

    public ObservableList<Ride> getRModel() {
        populateRideList();
        return rModel;
    }

    public ObservableList<RBooking> getRBModel(String destination, String date, String hour) {
        ObservableList<RBooking> rbModel = FXCollections.observableArrayList();
        RBooking[] rbs = server.findAllRBookings(destination, date, hour);
        for (RBooking rb : rbs) {
            rbModel.add(rb);
        }
        //forEach(x -> rbModel.add(x));
        rbModelList.add(new Pair<>(rbModel, Arrays.asList(destination, date, hour)));
        return rbModel;
    }

    public void findUser(String username, String password) {
        User user = new User(username, password);
        server.login(user, this);
        this.user = user;
    }

    public void logout(User u) {
        server.logout(u, this);
    }

    public String bookPlaces(Ride r, String cname, int nrplaces) {
        return server.bookPlaces(r, new Client(0, cname), nrplaces);
    }

    private void upd(Pair<ObservableList<RBooking>, List<String>> pair) {
        pair.getKey().clear();
        RBooking[] rbs = server.findAllRBookings(pair.getValue().get(0), pair.getValue().get(1), pair.getValue().get(2));
        for (RBooking rb : rbs) {
            pair.getKey().add(rb);
        }
    }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                populateRideList();
                rbModelList.forEach(x -> upd(x));
            }
        });
    }
}
