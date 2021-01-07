package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import sample.domain.Client;
import sample.domain.Location;
import sample.domain.Location1;
import sample.service.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Controller {
    @FXML
    ComboBox<String> comboLocatie;
    @FXML
    TableView<Location1> table;
    private Service service;
    private ObservableList<Location1> lModel = FXCollections.observableArrayList();
    ;
    @FXML
    private ComboBox<Perioada> comboPerioada;
    @FXML
    private Button cautaBtn;

    public Controller() {
    }

    public void setService(Service service) {
        this.service = service;
        populateModel();
        initData();
    }

    private void populateModel() {
        service.getAllLoc().forEach(x -> lModel.add(new Location1(x.getLocationName(), x.getHotel(), service.getNrNopti(x.getLocationName()))));
    }

    @FXML
    public void initialize() {
        comboPerioada.setItems(FXCollections.observableArrayList(Perioada.values()));
    }

    private void initData() {
        comboLocatie.setItems(FXCollections.observableArrayList(service.getAllLocations()));
        table.getItems().clear();
        for (Location r : service.getAllLoc()) {
            Location1 re = new Location1(r.getLocationName(), r.getHotel(), service.getNrNopti(r.getLocationName()));
            table.getItems().add(re);
        }
    }

    @FXML
    private void cautaHoteluri() {
        String location = comboLocatie.getSelectionModel().getSelectedItem();
        Perioada p = comboPerioada.getSelectionModel().getSelectedItem();
        FilteredList<Location1> flist = new FilteredList<>(lModel, x -> true);
        Predicate<Location1> predicate = l -> {
            if (location != null)
                if (!l.getLocationName().equals(location))
                    return false;
            return true;
        };
        flist.setPredicate(predicate);
        table.setItems(flist);
    }

    public List<Location> getLocations(String location) {
        return service.getLocations(location);
    }

    public Map<Client, Long> getClientNrReservations() {
        return service.getClientNrReservations();
    }

    public Map<String, Double> getSumaIncasataHotel() {
        return service.getSumaIncasataHotel();
    }
}
