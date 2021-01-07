package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import factory.*;
import javafx.util.Pair;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

/**
 * class responsible for
 */
public class Service {
    private RepositoryInterface<Integer,Student> sRepo;
    private RepositoryInterface<Integer,Tema> tRepo;
    private RepositoryInterface<Pair<Integer,Integer>,Nota> nRepo;

    /**
     * constructor
     *
     * @param r1 - Repository for entities of Student type
     * @param r2 - Repository for entities of Tema type
     */
    public Service( AbstractRepository<Integer,Student> r1, TemaRepository r2, NotaRepository r3) {
        sRepo = r1;
        tRepo = r2;
        nRepo = r3;
    }

    /**
     * @param id - int id for a Student
     * @return the Student entity with the id 'id' or null if there is no entity for that id
     */
    public Optional<Student> findOneStudent(int id) {
        return sRepo.findOne(id);
    }

    /**
     * @param id         - the student id
     * @param nume       - student's name
     * @param grupa      - student's group
     * @param email      - student's email
     * @param indrumator - student's teacher
     * @return the entity that was updated if there is already a student with that id or null otherwise
     */
    public Student addStudent(int id, String nume, int grupa, String email, String indrumator) {
        return sRepo.save(new Student(id, nume, grupa, email, indrumator));
    }

    /**
     * @param id         - id for student to be updated
     * @param nume       - the new name
     * @param grupa      - the new group
     * @param email      - the new email
     * @param indrumator - the new teacher
     * @return null if there is a student that has the id 'id' or the student otherwise
     */
    public Optional<Student> updateStudent(int id, String nume, int grupa, String email, String indrumator) {
        return sRepo.update(new Student(id, nume, grupa, email, indrumator));
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
     * @param id - id for the student to be deleted
     * @return null if there is no student with that id or the deleted student otherwise
     */
    public Optional<Student> deleteStudent(int id) {
        return sRepo.delete(id);
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
     * @param nr        - homework's id
     * @param descriere - homework's description
     * @param deadline  - homework's deadline
     * @param sPrimire  - homework's receiving week
     * @return the entity that was updated if there is already a Tema entity with that id or null otherwise
     */
    public Tema addTema(int nr, String descriere, int deadline, int sPrimire) {
        return tRepo.save(new Tema(nr, descriere, deadline, sPrimire));
    }

    /**
     * @param nr        - homework's id to be updated
     * @param descriere - new description
     * @param deadline  - new deadline
     * @param sPrimire  - new receiving week
     * @return null if there is a Tema object with that id or the Tema object otherwise
     */
    public Optional<Tema> updateTema(int nr, String descriere, int deadline, int sPrimire) {
        return tRepo.update(new Tema(nr, descriere, deadline, sPrimire));
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
     * @param id - id for the homework to be deleted
     * @return null if there is no homework with that id or the deleted homework otherwise
     */
    public Optional<Tema> deleteTema(int id) {
        return tRepo.delete(id);
    }

    /**
     * extends the deadline for an existing homework, increasing by 1 the homework deadline
     * if the homework does not exist  or the current week exceeded the homework deadline,
     * an IllegalArgumentException is thrown
     *
     * @param id - the id of the entity for which the deadline will be changed
     */
    public void prelungesteTermen(int id) {
        int week = getCurrentWeek();
        Optional<Tema> t = tRepo.findOne(id);
        if (!t.isPresent())
            throw new IllegalArgumentException("Tema inexistenta!\n");
        if (week < t.get().getDeadline()) {
            t.get().setDeadline(t.get().getDeadline() + 1);
            tRepo.update(t.get());
        } else
            throw new IllegalArgumentException("Numarul saptamanii curente a depasit deadline-ul!\n");
    }

    /**
     * when a grade is added in the catalog it is added in the student's file too
     *
     * @param nume     - student's name
     * @param idT      - homework number
     * @param n        - grade's value
     * @param deadline - homework's deadline
     * @param feedback
     */
    private void makeStudentFile(String nume, int idT, double n, int deadline, String feedback) {
        File file = new File("D:\\A2S1\\MAP\\Laboratoare\\Lab5\\src\\main\\java\\studentFile/" + nume.replace(" ", "") + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Nu s-a putut creea fisierul!\n");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write("Tema: " + idT + "\n");
            bw.write("Nota: " + n + "\n");
            bw.write("Predata in saptamana: " + getCurrentWeek() + "\n");
            bw.write("Deadline: " + deadline + "\n");
            bw.write("Feedback: " + feedback + "\n");
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Eroare la scrierea in fisier!\n");
        }
    }


    public double addNota(int idS, int idT, double nota, LocalDateTime date, String feedback) {
        Optional<Tema> t;
        Optional<Student> s;
        if (!(s = sRepo.findOne(idS)).isPresent())
            throw new IllegalArgumentException("Nu exista student cu id-ul " + idS + "\n");
        if (!(t = tRepo.findOne(idT)).isPresent())
            throw new IllegalArgumentException("Nu exista tema cu numarul " + idT + "\n");
        int depunctari = getCurrentWeek() - t.get().getDeadline();
        if (depunctari < 0)
            depunctari = 0;
        if (depunctari > 2)
            throw new IllegalArgumentException("Tema nu mai poate fi predata!\n");
        double n = nota - 2.5 * depunctari;
        double n1 = 10 - 2.5*depunctari;
        if (n < 1)
            n = 1;
        nRepo.save(new Nota(idS, idT, n, date));
        makeStudentFile(s.get().getNume(), idT, n, t.get().getDeadline(), feedback);
        return n1;
    }
}