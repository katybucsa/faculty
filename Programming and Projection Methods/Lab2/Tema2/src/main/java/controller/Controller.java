package controller;

import domain.Booking;
import domain.Client;
import domain.Ride;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import repository.ValidationException;
import service.Service;
import utils.Observer;
import utils.AppEvent;
import utils.RBooking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller implements Observer<AppEvent> {
    private Service service;
    private ObservableList<Ride> rModel;
    private List<Pair<ObservableList<RBooking>, List<String>>> rbModelList;

    public Controller(Service service) {
        this.service = service;
        service.addObserver(this);
        rModel = FXCollections.observableArrayList();
        rbModelList = new ArrayList<>();
        populateRideList();
    }

    private void populateRideList() {
        rModel.clear();
        service.findAllRides().forEach(x -> rModel.add(x));
    }

    public ObservableList<Ride> getRModel() {
        return rModel;
    }

    public ObservableList<RBooking> getRBModel(String destination, String date, String hour) {
        ObservableList<RBooking> rbModel = FXCollections.observableArrayList();
        service.findAllRBookings(destination, date, hour).forEach(x -> rbModel.add(x));
        rbModelList.add(new Pair<>(rbModel,Arrays.asList(destination, date, hour)));
        return rbModel;
    }

    public void findUser(String username, String password) {
        try (Socket socket = new Socket("localhost", 55555)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = username + "|" + password + "\n";
            writer.println(s);
            writer.flush();
            String received = br.readLine();
            if(!received.equals("ok"))
                throw new ValidationException(received);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String bookPlaces(Ride r, String cname, int nrplaces) {
        return service.bookPlaces(r, new Client(0, cname), nrplaces);
    }

    private void upd(Pair<ObservableList<RBooking>,List<String>> pair) {
        pair.getKey().clear();
        service.findAllRBookings(pair.getValue().get(0),pair.getValue().get(1),pair.getValue().get(2))
                .forEach(x->pair.getKey().add(x));
    }

    @Override
    public void update(AppEvent appEvent) {
        switch (appEvent.getAction()) {
            case UPDATE: {
                populateRideList();
                rbModelList.forEach(x -> upd(x));
            }
        }
    }
}
