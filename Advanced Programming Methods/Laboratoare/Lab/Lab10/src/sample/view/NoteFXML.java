package sample.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.domain.Nota;
import sample.domain.Student;
import sample.domain.Tema;
import sample.service.Service;
import sample.utils.Action;
import sample.utils.AppEvent;
import sample.utils.Observer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NoteFXML implements Observer<AppEvent> {
    private Service service;
    @FXML
    private TableView<Nota> tabel;
    @FXML
    private javafx.scene.control.TextArea feedback;
    @FXML
    private Text temaTxt, motivatTxt, notaTxt, feedbackTxt;
    @FXML
    private TextField notaF;
    @FXML
    private ComboBox<String> comboTeme;
    @FXML
    private RadioButton motivat;

    public NoteFXML() {
    }

    public void setService(Service s) {
        service = s;
        service.addObserver(this);
        //initData();
    }

    /*@FXML
    public void initialize() {
        //comboTeme.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        //comboTeme.setValue(new GregorianCalendar().get(Calendar.WEEK_OF_YEAR)-39);
        //comboTeme.setItems(FXCollections.observableArrayList(getDescs()));
    }


    private ArrayList<String> getDescs(){
        ArrayList<String> descs=new ArrayList<>();
        service.findAllTeme().forEach(x->descs.add(x.getDescriere()));
        return descs;
    }
   private void initData(){

    }
*/

    @Override public void update(AppEvent ae){

    }
}
