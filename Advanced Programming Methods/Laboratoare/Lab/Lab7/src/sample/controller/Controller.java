package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.domain.Student;
import sample.domain.Tema;
import sample.service.Service;
import sample.utils.AppEvent;
import sample.utils.Observer;

import java.util.Optional;

public class Controller implements Observer<AppEvent> {
    private Service serv;
    private ObservableList<Student> sModel;
    private ObservableList<Tema> tModel;

    public Controller(Service s) {
        serv = s;
        serv.addObserver(this);
        sModel = FXCollections.observableArrayList();
        tModel = FXCollections.observableArrayList();
        populateStudentsList();
        populateTemaList();
    }

    private void populateStudentsList() {
        serv.findAllStudents().forEach(x -> sModel.add(x));
    }

    private void populateTemaList() {
        serv.findAllTeme().forEach(x -> tModel.add(x));
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

    public Iterable<Tema> findAllTeme(){
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
     *
     * @param tema - the entity for which the deadline will be changed
     */
    public void prelungesteTermen(Tema tema) {
        serv.prelungesteTermen(tema);
    }

    public ObservableList<Student> getSModel() {
        return sModel;
    }

    public ObservableList<Tema> getTModel(){
        return tModel;
    }

    @Override
    public void update(AppEvent appEvent) {
        switch (appEvent.getAction()) {
            case ADDS: {
                sModel.add((Student)appEvent.getData());
                break;
            }
            case DELETES: {
                sModel.remove(appEvent.getData());
                break;
            }
            case UPDATES: {
                sModel.remove(appEvent.getOldData());
                sModel.add((Student)appEvent.getData());
                break;
            }
            case ADDT: {
                tModel.add((Tema)appEvent.getData());
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
        }
    }
}