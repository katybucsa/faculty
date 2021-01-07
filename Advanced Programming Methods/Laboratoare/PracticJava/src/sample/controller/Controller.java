package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import sample.domain.Client;
import sample.domain.Book;
import sample.domain.Location1;
import sample.domain.Type;
import sample.service.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Controller {
    @FXML
    TableView<Location1> table;
    private Service service;
    @FXML
    DatePicker startDate,finalDate;
    private ObservableList<Location1> lModel = FXCollections.observableArrayList();
    @FXML
    private Button cautaBtn;

    public Controller() {
    }

    public void setService(Service service) {
        this.service = service;
       // populateModel();
        //initData();
    }

    public Map<Type,List<Book>> getBooksByType(){
        return service.getBooksByType();
    }

    public Integer getNrCarti(String name){
        return service.getNrCarti(name);
    }

    public List<Book> getFaraInchiriere(int year) {
        return service.getFaraInchiriere(year);
    }

   /* private void populateModel() {
        service.getAllLoc().forEach(x -> lModel.add(new Location1(x.getLocationName(), x.getHotel(), service.getNrNopti(x.getLocationName()))));
    }
*/
    @FXML
    public void initialize() {

    }

    private void initData() {

    }

    @FXML
    private void cautaHoteluri() {
        LocalDate ld1=startDate.getValue();
        LocalDate ld2=finalDate.getValue();
    }


}
