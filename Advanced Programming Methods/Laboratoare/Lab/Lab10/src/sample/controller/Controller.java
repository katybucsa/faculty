package sample.controller;

import javafx.beans.property.ReadOnlyObjectPropertyBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.ReadOnlyJavaBeanObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.domain.Nota;
import sample.domain.Nota2;
import sample.domain.Student;
import sample.domain.Tema;
import sample.repository.RepoException;
import sample.repository.ValidationException;
import sample.service.Service;
import sample.utils.AppEvent;
import sample.utils.Observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller implements Observer<AppEvent> {
    @FXML
    private Button addBtn,clearFilt,addToAll,filterdate;
    @FXML
    private DialogPane dialogP;
    private Service serv;
    private ObservableList<Student> sModel;
    private ObservableList<Tema> tModel;
    private ObservableList<Nota2> nModel;
    @FXML
    private TableView<Nota2> tabel;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextArea feedback;
    @FXML
    private TextField numeF, notaF, filtNumeText;
    @FXML
    private ComboBox<Integer> comboTeme;
    @FXML private ComboBox<String> comboFiltTema, comboFiltGrupa;
    @FXML
    private RadioButton lipsit, motivat, nemotivat;
    @FXML
    ToggleGroup absGroup;
    @FXML private DatePicker filtInceput,filtSfarsit;

    public Controller() {
    }

    static void showErrorMessage(String text) {
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("A aparut o eroare");
        message.setContentText(text);
        message.showAndWait();

    }

    static boolean showConfirmationMessage(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Adaugare nota");
        alert.setContentText(text);
        Optional<ButtonType> type = alert.showAndWait();
        if (type.get() == ButtonType.CANCEL)
            return false;
        return true;
    }

    public void setService(Service s) {
        serv = s;
        serv.addObserver(this);
        sModel = FXCollections.observableArrayList();
        tModel = FXCollections.observableArrayList();
        nModel = FXCollections.observableArrayList();
        populateStudentsList();
        populateTemaList();
        populateNotaList();
        initData();
    }

    @FXML
    public void initialize() {
        comboTeme.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        numeF.textProperty().addListener((observable, oldValue, newValue) -> showListView(newValue));
        lipsit.selectedProperty().addListener((observable, oldvalue, newValue) -> {
            if (newValue) {
                motivat.setSelected(true);
                motivat.setDisable(false);
                nemotivat.setDisable(false);
                return;
            }
            motivat.setSelected(false);
            nemotivat.setSelected(false);
            motivat.setDisable(true);
            nemotivat.setDisable(true);
        });
        nemotivat.selectedProperty().addListener((observable, oldvalue, newValue) -> addToFeedback1(newValue));
        motivat.selectedProperty().addListener((observable, oldvalue, newValue) -> addToFeedback2(newValue));
        tabel.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> showNotaDetails(newValue));
        tabel.setRowFactory(tv -> changeSelection());
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> addToName(newValue));
        comboFiltTema.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"));
        comboFiltGrupa.setItems(FXCollections.observableArrayList("221","222","223","224","225","226","227"));
       // filtInceput.getProperties().addListener()
    }

    @FXML
    private void showHomPerioada(){
        FilteredList<Nota2> flist=new FilteredList<>(nModel,p->true);
        LocalDate ld1=filtInceput.getValue();
        LocalDate ld2=filtSfarsit.getValue();
        Predicate<Nota2> p=n->{
            if(n.getData().toLocalDate().isBefore(ld1))
                return false;
            if(n.getData().toLocalDate().isAfter(ld2))
                return false;
            return true;
        };
        flist.setPredicate(p);
        tabel.setItems(flist);
    }

    private void initData() {
        ChangeListener<String> cl=(observable, oldValue, newValue) ->filterList(newValue);
        filtNumeText.textProperty().addListener(cl);
        comboFiltTema.getSelectionModel().selectedItemProperty().addListener(cl);
        comboFiltGrupa.getSelectionModel().selectedItemProperty().addListener(cl);
        tabel.getItems().clear();
        for (Nota nota : serv.findAllN()){
            Nota2 n=new Nota2(nota.getIdS(),nota.getNrT(),serv.findOneStudent(nota.getIdS()).get().getNume(),nota.getNota(),nota.getData(),nota.getFeedback());
            tabel.getItems().add(n);
        }

        serv.findAllStudents().forEach(s -> listView.getItems().add(s.getNume()));
        comboTeme.setValue(serv.getCurrentHomework());
    }


    private void filterList(String newValue){
        FilteredList<Nota2> flist=new FilteredList<>(nModel,p->true);
        if(newValue!=null) {
            Predicate<Nota2> p = n -> {
                if (!filtNumeText.getText().isEmpty()){
                    if(!serv.findOneStudent(n.getIdS()).get().getNume().toLowerCase().contains(filtNumeText.getText().toLowerCase()))
                        return false;
                }
                if(!comboFiltTema.getSelectionModel().isEmpty()) {
                    if (!n.getNrT().toString().equals(comboFiltTema.getSelectionModel().getSelectedItem()))
                        return false;
                }
                if(!comboFiltGrupa.getSelectionModel().isEmpty()){
                    if(!String.valueOf(serv.findOneStudent(n.getIdS()).get().getGrupa()).equals(comboFiltGrupa.getSelectionModel().getSelectedItem()))
                        return false;
                }
                return true;
            };
            flist.setPredicate(p);
            tabel.setItems(flist);
        }
    }

    private TableRow<Nota2> changeSelection() {
        TableRow<Nota2> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && row.isEmpty()) {
                tabel.getSelectionModel().clearSelection();
                lipsit.setSelected(false);
                numeF.setText("");
                listView.setVisible(false);
            }
        });
        return row;
    }

    private void showListView(String value) {
        FilteredList<Student> flist = new FilteredList<>(sModel, p -> true);
        ObservableList<String> nlist = FXCollections.observableArrayList();
        if (value.equals("")) {
            listView.setVisible(false);
            return;
        }
        flist.setPredicate(p -> p.getNume().toLowerCase().startsWith(value.toLowerCase()));
        flist.forEach(x -> nlist.add(x.getNume()));
        listView.setItems(nlist);
        if (!listView.getItems().isEmpty())
            listView.setVisible(true);
        else
            listView.setVisible(false);
    }

    private void addToName(String value) {
        if (value == null) {
            return;
        }
        numeF.setText("" + value);
        listView.setVisible(false);
    }

    private void addToFeedback1(Boolean value) {
        if (value)
            feedback.setText("NOTA A FOST DIMINUATA CU n PUNCTE DIN CAUZA INTARZIERILOR");
        else
            feedback.setText("");
    }

    private void addToFeedback2(Boolean value) {
        if (value)
            feedback.setText("NOTA NU A FOST DIMINUATA DIN CAUZA INTARZIERILOR DEOARECE ABSENTA A FOST MOTIVATA");
        else
            feedback.setText("");
    }

    private void showNotaDetails(Nota2 nota) {
        if (nota == null) {
            clearFields();
            return;
        }
        comboTeme.setValue(serv.findOneTema(nota.getNrT()).get().getDeadline());
        numeF.setText(serv.findOneStudent(nota.getIdS()).get().getNume());
        if (nota.getFeedback().matches("NOTA A FOST DIMINUATA CU [0-9][.][0-9] PUNCTE DIN CAUZA INTARZIERILOR")) {
            lipsit.setSelected(true);
            motivat.setSelected(true);
        } else if (nota.getFeedback().contains("NOTA NU A FOST DIMINUATA DIN CAUZA INTARZIERILOR DEOARECE ABSENTA A FOST MOTIVATA")) {
            lipsit.setSelected(true);
            motivat.setSelected(false);
        } else
            lipsit.setSelected(false);
        notaF.setText("" + nota.getNota());
        feedback.setText("" + nota.getFeedback());
        listView.setVisible(false);
    }

    @FXML
    private void clear() {
        tabel.getSelectionModel().clearSelection();
        listView.setVisible(false);
    }

    private void clearFields() {
        comboTeme.setValue(serv.getCurrentHomework());
        motivat.setSelected(false);
        numeF.setText("");
        notaF.setText("");
        feedback.setText("");
        filtNumeText.setText("");
        comboFiltTema.getSelectionModel().clearSelection();
        comboFiltGrupa.getSelectionModel().clearSelection();
    }

    @FXML
    private void addButton() {
        try {
            int idt = comboTeme.getSelectionModel().getSelectedItem();
            Student s = serv.getStudentByName(numeF.getText());
            double nota = Float.parseFloat(notaF.getText());
            String fb = feedback.getText();
            Boolean flag = nemotivat.isSelected();
            String penalizare="";
            if(lipsit.isSelected() && motivat.isSelected())
                penalizare="Nu se aplica penalizari deoarece studentul a lipsit motivat!";
            else if(lipsit.isSelected() && nemotivat.isSelected())
                penalizare="S-a aplicat penlizarea de "+((serv.getCurrentWeek()-serv.findOneTema(idt).get().getDeadline())*2.5)+"din cauza intarzierilor";
            if(showConfirmationMessage("Tema cu numarul "+idt+ "\nStudentul:"+numeF.getText()+"\n"+penalizare)) {
                {
                    if(numeF.equals("") || fb.equals(""))
                        showErrorMessage("Campuri vide!");
                    else {
                        addNota(s.getID(), idt, nota, LocalDateTime.now(), fb, flag);
                        clearFields();
                    }
                }
            }
        } catch (NumberFormatException nfe) {
            showErrorMessage("Deadline si nota trebuie sa fie numere!");
        } catch (IllegalArgumentException iae) {
            showErrorMessage(iae.getMessage());
        } catch (ValidationException ve) {
            showErrorMessage(ve.getMessage());
        } catch (RepoException re) {
            showErrorMessage(re.getMessage());
        }
    }

    @FXML private void clearFilters(){
        tabel.setItems(nModel);
        clearFields();
    }

    @FXML private void addGradeToAll(){

    }

    private void addNota(int ids, int idt, double nota, LocalDateTime ldt, String feedback, Boolean flag) {
        serv.addNota(ids, idt, nota, ldt, feedback, flag);
    }

    private void populateStudentsList() {
        serv.findAllStudents().forEach(x -> sModel.add(x));
    }

    private void populateTemaList() {
        serv.findAllTeme().forEach(x -> tModel.add(x));
    }

    private void populateNotaList(){
        serv.findAllN().forEach(nota->nModel.add(new Nota2(nota.getIdS(),nota.getNrT(),serv.findOneStudent(nota.getIdS()).get().getNume(),nota.getNota(),nota.getData(),nota.getFeedback())));
    }

    /**
     * @param id - int id for a Student
     * @return the Student entity with the id 'id' or null if there is no entity for that id
     */
    public Optional<Student> findOneStudent(int id) {
        return serv.findOneStudent(id);
    }

    /**
     * @param id - int id for a Tema object
     * @return the Tema entity with the id 'id' or null if there is no Tema entity for that id
     */
    public Optional<Tema> findOneTema(int id) {
        return serv.findOneTema(id);
    }

    /**
     * @param id         - the student id
     * @param nume       - student's name
     * @param grupa      - student's group
     * @param email      - student's email
     * @param indrumator - student's teacher
     */
    public void addStudent(int id, String nume, int grupa, String email, String indrumator) {
        Student student = new Student(id, nume, grupa, email, indrumator);
        serv.addStudent(student);
    }

    /**
     * @param nr        - homework's id
     * @param descriere - homework's description
     * @param deadline  - homework's deadline
     * @param sPrimire  - homework's receiving week
     */
    public void addTema(int nr, String descriere, int deadline, int sPrimire) {
        serv.addTema(new Tema(nr, descriere, deadline, sPrimire));
    }


    /**
     * @param id         - id for student to be updated
     * @param nume       - the new name
     * @param grupa      - the new group
     * @param email      - the new email
     * @param indrumator - the new teacher
     */
    public void updateStudent(Student old_st, int id, String nume, int grupa, String email, String indrumator) {
        Student student = new Student(id, nume, grupa, email, indrumator);
        serv.updateStudent(old_st, student);
    }

    /**
     * @param t         - old homework
     * @param nr        - homework's id to be updated
     * @param descriere - new description
     * @param deadline  - new deadline
     * @param sPrimire  - new receiving week
     */
    public void updateTema(Tema t, int nr, String descriere, int deadline, int sPrimire) {
        serv.updateTema(t, new Tema(nr, descriere, deadline, sPrimire));
    }


    /**
     * @return all Student entities
     */
    public Iterable<Student> findAllStudents() {
        return serv.findAllStudents();
    }

    public Iterable<Tema> findAllTeme() {
        return serv.findAllTeme();
    }

    /**
     * @param s - student to be deleted
     */
    public void deleteStudent(Student s) {
        serv.deleteStudent(s);
    }

    /**
     * @param t - homework to be deleted
     */
    public void deleteTema(Tema t) {
        serv.deleteTema(t);
    }


    /**
     * @param tema - the entity for which the deadline will be changed
     */
    public void prelungesteTermen(Tema tema) {
        serv.prelungesteTermen(tema);
    }

    public ObservableList<Student> getSModel() {
        return sModel;
    }

    public ObservableList<Tema> getTModel() {
        return tModel;
    }

    @Override
    public void update(AppEvent appEvent) {
        switch (appEvent.getAction()) {
            case ADDS: {
                sModel.add((Student) appEvent.getData());
                break;
            }
            case DELETES: {
                sModel.remove(appEvent.getData());
                break;
            }
            case UPDATES: {
                sModel.remove(appEvent.getOldData());
                sModel.add((Student) appEvent.getData());
                break;
            }
            case ADDT: {
                tModel.add((Tema) appEvent.getData());
                break;
            }
            case DELETET: {
                tModel.remove(appEvent.getData());
                break;
            }
            case UPDATET: {
                tModel.remove(appEvent.getOldData());
                tModel.add((Tema) appEvent.getData());
                break;
            }
            case ADDN:{ tabel.getItems().add((Nota2)appEvent.getData()); break;}
        }
    }
}