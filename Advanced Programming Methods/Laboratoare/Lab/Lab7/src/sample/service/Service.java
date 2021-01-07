package sample.service;

import javafx.util.Pair;
import sample.domain.Nota;
import sample.domain.Student;
import sample.domain.Tema;
import sample.repository.*;
import sample.utils.Action;
import sample.utils.AppEvent;
import sample.utils.Observable;
import sample.utils.Observer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * class responsible for
 */
public class Service implements Observable<AppEvent> {
    private RepositoryInterface<Integer, Student> sRepo;
    private RepositoryInterface<Integer, Tema> tRepo;
    private RepositoryInterface<Pair<Integer, Integer>, Nota> nRepo;
    private List<Observer<AppEvent>> observers = new ArrayList<>();

    /**
     * constructor
     *
     * @param r1 - Repository for entities of Student type
     * @param r2 - Repository for entities of Tema type
     */
    public Service(AbstractRepository<Integer, Student> r1, TemaRepository r2, NotaRepository r3) {
        sRepo = r1;
        tRepo = r2;
        nRepo = r3;
    }

    public Optional<Student> findOneStudent(int id) {
        return sRepo.findOne(id);
    }

    public void addStudent(Student student) {
        sRepo.save(student);
        notifyObservers(new AppEvent(Action.ADDS, student));
    }

    public void updateStudent(Student old, Student student) {
        sRepo.update(student);
        notifyObservers(new AppEvent(Action.UPDATES, student, old));
    }

    /**
     * @return the number of students in Repo
     */
    public int sizeStudent() {
        return sRepo.size();
    }

    /**
     * @return all Student entities
     */
    public Iterable<Student> findAllStudents() {
        return sRepo.findAll();
    }

    /**
     * @param s - student to be deleted
     * @return null if there is no student with that id or the deleted student otherwise
     */
    public void deleteStudent(Student s) {
        sRepo.delete(s.getID());
        notifyObservers(new AppEvent(Action.DELETES, s));
    }

    /**
     * @return the number of the current week of the university year
     */
    private int getCurrentWeek() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return calendar.get(Calendar.WEEK_OF_YEAR) - 39;
    }

    /**
     * @param id - int id for a Tema object
     * @return the Tema entity with the id 'id' or null if there is no Tema entity for that id
     */
    public Optional<Tema> findOneTema(int id) {
        return tRepo.findOne(id);
    }

    /**
     * @param tema - homework to be added
     */
    public void addTema(Tema tema) {
        tRepo.save(tema);
        notifyObservers(new AppEvent(Action.ADDT, tema));
    }

    /**
     * @param old - homework to be updated
     * @param nou - new homework
     */
    public void updateTema(Tema old, Tema nou) {
        tRepo.update(nou);
        notifyObservers(new AppEvent(Action.UPDATET, nou, old));
    }

    /**
     * @return the number of homework stored in Repo
     */
    public int sizeTema() {
        return tRepo.size();
    }

    /**
     * @return all Tema entities from Repo
     */
    public Iterable<Tema> findAllTeme() {
        return tRepo.findAll();
    }

    /**
     * @param t - homework to be deleted
     */
    public void deleteTema(Tema t) {
        tRepo.delete(t.getID());
        notifyObservers(new AppEvent(Action.DELETET, t));
    }

    /**
     * extends the deadline for an existing homework, increasing by 1 the homework deadline
     * if  the current week exceeded the homework deadline, an IllegalArgumentException is thrown
     * @param t - entity for which the deadline will be changed
     */
    public void prelungesteTermen(Tema t) {
        int week = getCurrentWeek();
        Tema t1;
        if (week < t.getDeadline()) {
            t1=new Tema(t.getID(),t.getDescriere(),t.getDeadline()+1,t.getSaptPrimireTema());
            tRepo.update(t1);

        } else
            throw new IllegalArgumentException("Numarul saptamanii curente a depasit deadline-ul!\n");
        notifyObservers(new AppEvent(Action.UPDATET,t1,t));
    }

//    /**
//     * when a grade is added in the catalog it is added in the student's file too
//     *
//     * @param nume     - student's name
//     * @param idT      - homework number
//     * @param n        - grade's value
//     * @param deadline - homework's deadline
//     * @param feedback
//     */
//    private void makeStudentFile(String nume, int idT, double n, int deadline, String feedback) {
//        File file = new File("D:\\A2S1\\MAP\\Laboratoare\\Lab5\\src\\main\\java\\StudentFile/" + nume.replace(" ", "") + ".txt");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException("Nu s-a putut creea fisierul!\n");
//        }
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
//            bw.write("Tema: " + idT + "\n");
//            bw.write("Nota: " + n + "\n");
//            bw.write("Predata in saptamana: " + getCurrentWeek() + "\n");
//            bw.write("Deadline: " + deadline + "\n");
//            bw.write("Feedback: " + feedback + "\n");
//            bw.newLine();
//        } catch (IOException e) {
//            throw new RuntimeException("Eroare la scrierea in fisier!\n");
//        }
//    }
//
//
//    public double addNota(int idS, int idT, double nota, LocalDateTime date, String feedback) {
//        Optional<Tema> t;
//        Optional<Student> s;
//        if (!(s = sRepo.findOne(idS)).isPresent())
//            throw new IllegalArgumentException("Nu exista student cu id-ul " + idS + "\n");
//        if (!(t = tRepo.findOne(idT)).isPresent())
//            throw new IllegalArgumentException("Nu exista tema cu numarul " + idT + "\n");
//        int depunctari = getCurrentWeek() - t.get().getDeadline();
//        if (depunctari < 0)
//            depunctari = 0;
//        if (depunctari > 2)
//            throw new IllegalArgumentException("Tema nu mai poate fi predata!\n");
//        double n = nota - 2.5 * depunctari;
//        double n1 = 10 - 2.5*depunctari;
//        if (n < 1)
//            n = 1;
//        nRepo.save(new Nota(idS, idT, n, date));
//        makeStudentFile(s.get().getNume(), idT, n, t.get().getDeadline(), feedback);
//        return n1;
//    }

    public void addObserver(Observer<AppEvent> o) {
        observers.add(o);
    }

    public void removeObserver(Observer<AppEvent> o) {
        observers.remove(o);
    }

    public void notifyObservers(AppEvent e) {
        observers.stream().forEach(x -> x.update(e));
    }
}