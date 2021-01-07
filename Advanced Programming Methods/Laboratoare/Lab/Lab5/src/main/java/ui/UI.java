package ui;

import service.Service;
import domain.Student;
import domain.Tema;
import factory.RepoException;
import factory.ValidationException;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

/**
 * class responsible for reading from keyboard and printing on the screen
 */
public class UI {
    private Service serv;

    /**
     * constructor
     *
     * @param s - service
     */
    public UI(Service s) {
        serv = s;
    }

    /**
     * prints the app's menu
     */
    private void printMeniu() {
        System.out.println("\t\tStudenti\t\t\t\t\t\t\t\t\t\t\tTeme\t\t\t\t\t\t\t\t\tNote\n");
        System.out.println("1.  Gaseste student dupa id\t\t\t\t\t\t7.  Gaseste tema dupa numar\t\t\t\t\t\t14. Adauga o nota\n2.  Adauga un student\t\t\t\t\t\t\t8.  Adauga o tema\n3.  Modifica student\t\t\t\t\t\t\t9.  Modifica o tema\n4.  Afiseaza numarul de studenti\t\t\t\t10. Afiseaza numarul de teme\n" +
                "5.  Afiseaza lista studenti\t\t\t\t\t\t11. Afiseaza lista teme\n6.  Elimina un student\t\t\t\t\t\t\t12. Elimina o tema\n\t\t\t\t\t\t\t\t\t\t\t\t13. Prelungeste deadline pentru o tema\n");
        System.out.println("0.  Exit");
    }

    /**
     * @param mesaj - message to be printed on the screen
     * @return the int read from keyboard
     */
    private int readInt(String mesaj) {
        System.out.println(mesaj);
        return new Scanner(System.in).nextInt();
    }

    /**
     * @param mesaj - message to be printed on the screen
     * @return the double read from keyboard
     */
    private double readDouble(String mesaj) {
        System.out.println(mesaj);
        return new Scanner(System.in).nextDouble();
    }

    /**
     * @param mesaj -message to be printed on the screen
     * @return the String read from keyboard
     */
    private String readString(String mesaj) {
        System.out.println(mesaj);
        return new Scanner(System.in).nextLine();
    }

    /**
     * @param cmd - command number to be executed
     */
    private void execute(int cmd) {

        if (cmd == 1)
            findStudent();
        else if (cmd == 2)
            addStudent();
        else if (cmd == 3)
            updateStudent();
        else if (cmd == 4)
            numarStudenti();
        else if (cmd == 5)
            afiseazaStudenti();
        else if (cmd == 6)
            deleteStudent();
        else if (cmd == 7)
            findTema();
        else if (cmd == 8)
            addTema();
        else if (cmd == 9)
            updateTema();
        else if (cmd == 10)
            numarTeme();
        else if (cmd == 11)
            afiseazaTeme();
        else if (cmd == 12)
            deleteTema();
        else if (cmd == 13)
            prelungesteTermen();
        else if (cmd == 14)
            addNota();
        else
            System.out.println("Comanda invalida\n");
    }

    /**
     * prints the student with the id read from keyboard
     */
    private void findStudent() {
        int id;
        id = readInt("Introduceti id-ul studentului: ");
        Optional<Student> st = serv.findOneStudent(id);
        if (!st.isPresent())
            System.out.println("Nu exista student cu acest id!");
        else
            System.out.println(st.get());
        System.out.println("\n");
    }

    /**
     * reads data from keyboard for a student and sends it to service
     */
    private void addStudent() {
        int id, grupa;
        String nume, email, indrumator;
        id = readInt("Introduceti id student: ");
        nume = readString("Introduceti nume student: ");
        grupa = readInt("Introduceti grupa student: ");
        email = readString("Introduceti email student: ");
        indrumator = readString("Introduceti indrumator student: ");
        Student s = serv.addStudent(id, nume, grupa, email, indrumator);
        if (s == null)
            System.out.println("Studentul a fost adaugat cu succes!\n");
        else
            System.out.println("Studentul cu datele " + s + " a fost actualizat cu succes!\n");
    }

    /**
     * reads data from keyboard and sends it to service
     */
    private void updateStudent() {
        int id, grupa;
        String nume, email, indrumator;
        id = readInt("Introduceti id student: ");
        nume = readString("Introduceti nume student: ");
        grupa = readInt("Introduceti grupa student: ");
        email = readString("Introduceti email student: ");
        indrumator = readString("Introduceti indrumator student: ");
        Optional<Student> s = serv.updateStudent(id, nume, grupa, email, indrumator);
        if (!s.isPresent())
            System.out.println("Studentul a fost actualizat cu succes!\n");
        else
            System.out.println("Studentul cu id-ul " + id + " nu exista in lista de studenti, iar acesta a fost adaugat!\n");
    }

    /**
     * prints on the screen the number of students stored
     */
    private void numarStudenti() {
        System.out.println("Numarul studentilor este: " + serv.sizeStudent() + "\n");
    }

    /**
     * prints on the screen all students stored
     */
    private void afiseazaStudenti() {
        Iterable<Student> students = serv.findAllStudents();
        System.out.println("\n");
        System.out.println("Id\t\tNume\t\t\t\tGrupa\t\tEmail\t\t\t\t\tIndrumator");
        students.forEach(System.out::println);
        System.out.println("\n");
    }

    /**
     * reads from keyboard an id for the student to be deleted and sends it to service
     */
    private void deleteStudent() {
        int id = readInt("Introduceti id-ul studentului pe care vreti sa il eliminati din catalog: ");
        Optional<Student> s = serv.deleteStudent(id);
        if (!s.isPresent())
            System.out.println("Nu exista studentul cu id-ul " + id + "\n");
        else
            System.out.println("Studentul cu id-ul "+id+" a fost eliminat cu succes din catelog!\n");
    }


    //=====================================Tema============================================================

    /**
     * prints on the screen the Tema object for an id read from keyboard
     */
    private void findTema() {
        Scanner scanner = new Scanner(System.in);
        int id;
        System.out.println("Introduceti id-ul temei: ");
        id = scanner.nextInt();
        Optional<Tema> t = serv.findOneTema(id);
        if (!t.isPresent())
            System.out.println("Nu exista tema cu acest id!");
        else
            System.out.println(t.get());
        System.out.println("\n");
    }

    /**
     * reads from keyboard data for a Tema object and sends them to service
     */
    private void addTema() {
        int nr, deadline, sPrimire;
        String descriere;
        nr = readInt("Introduceti numarul temei: ");
        descriere = readString("Introduceti cerinta tema: ");
        deadline = readInt("Introduceti deadline tema: ");
        sPrimire = readInt("Introduceti numarul saptamanii de primire: ");
        Tema t = serv.addTema(nr, descriere, deadline, sPrimire);
        if (t == null)
            System.out.println("Tema a fost adaugata cu succes!\n");
        else
            System.out.println("Tema cu datele " + t + " a fost actualizata cu succes!\n");
    }

    /**
     * reads and int representing an id for Tema object and sends it to service
     */
    private void updateTema() {
        int nr, deadline, sPrimire;
        String descriere;
        nr = readInt("Introduceti numarul temei pe care doriti sa o modificati: ");
        descriere = readString("Introduceti cerinta tema: ");
        deadline = readInt("Introduceti deadline tema: ");
        sPrimire = readInt("Introduceti numarul saptamanii de primire: ");
        Optional<Tema> t = serv.updateTema(nr, descriere, deadline, sPrimire);
        if (!t.isPresent())
            System.out.println("Tema a fost actualizata cu succes!\n");
        else
            System.out.println("Tema cu numarul " + nr + " nu exista in lista de tema, iar acesta a fost adaugata!\n");
    }

    /**
     * prints the number of Tema object stored
     */
    private void numarTeme() {
        System.out.println("Numarul temelor este: " + serv.sizeTema() + "\n");
    }

    /**
     * prints on the screen all Tema entities stored
     */
    private void afiseazaTeme() {
        Iterable<Tema> teme = serv.findAllTeme();
        System.out.println("\n");
        System.out.println("Numar tema\t\tCerinta\t\t\t\tDeadline\t\tSaptamana primire");
        teme.forEach(System.out::println);
        System.out.println("\n");
    }

    /**
     * reads an int representing the id for the Tema object to be deleted and sends it to service
     */
    private void deleteTema() {
        int nr = readInt("Introduceti numarul temei pe care doriti sa o eliminati: ");
        Optional<Tema> t = serv.deleteTema(nr);
        if (!t.isPresent())
            System.out.println("Nu exista studentul cu id-ul " + nr + "\n");
        else
            System.out.println("Studentul cu datele " + t.get() + " a fost eliminat cu succes din catelog!\n");
    }

    /**
     * reads an int representing the id for the homework whose deadline will be extended
     */
    private void prelungesteTermen() {
        int nr = readInt("Introduceti numarul temei pentru care doriti sa prelungiti deadline-ul: ");
        serv.prelungesteTermen(nr);
        System.out.println("Deadline-ul temei cu numarul " + nr + "a fost prelungit cu o saptamana");
    }

    //=========================================Note=========================================================

    /**
     * reads 3 numbers representing the student id, the homework number and the student's grade for that homework
     * and a string, the feedback for that homework and sends this information to service
     */
    private void addNota() {
        int idS = readInt("Introduceti id-ul studentului caruia ii dati nota:");
        int idT = readInt("Introduceti numarul temei pentru care dati nota:");
        double nota = readDouble("Introduceti nota:");
        String feedback = readString("Introduceti feedback nota:");
        LocalDateTime date = LocalDateTime.now();
        double n = serv.addNota(idS, idT, nota, date, feedback);
        System.out.println("Nota a fost adaugata cu succes!\nIn urma eventualelor depunctari nota obtinuta este: " + n + "\n");
    }

    /**
     * the app's runner
     * catches the exceptions and prints their message on the screen
     */
    public void run() {
        int cmd;
        while (true) {
            try {
                printMeniu();
                cmd = readInt("Introduceti comanda: ");
                if (cmd == 0)
                    return;
                execute(cmd);
            } catch (RepoException re) {
                System.out.println(re.getMessage());
            } catch (InputMismatchException ime) {
                System.out.println("\nTip de data invalid!\n");
            } catch (ValidationException ve) {
                System.out.println(ve.getMessage());
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
    }
}