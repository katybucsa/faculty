import Service.Service;
import UI.UI;
import factory.*;

public class Main {

    public static void main(String[] args) {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();
        FileStudentRepo studentRepository = new FileStudentRepo(studentValidator, "D:\\MAP\\Laboratoare\\Lab3\\src\\main\\java/studenti.txt");
        FileTemaRepo temaRepository = new FileTemaRepo(temaValidator,"D:\\MAP\\Laboratoare\\Lab3\\src\\main\\java/teme.txt");
        FileNotaRepo notaRepository = new FileNotaRepo(notaValidator,"D:\\MAP\\Laboratoare\\Lab3\\src\\main\\java/catalog.txt");
        Service service = new Service(studentRepository,temaRepository,notaRepository);
        UI ui = new UI(service);
        ui.run();
    }
}
