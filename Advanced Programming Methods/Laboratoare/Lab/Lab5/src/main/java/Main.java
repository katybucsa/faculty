import service.Service;
import ui.UI;
import factory.*;

public class Main {

    public static void main(String[] args) {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();
        XmlStudentRepo studentRepository = new XmlStudentRepo(studentValidator, "D:\\A2S1\\MAP\\Laboratoare\\Lab5\\src\\main\\java/studenti.xml");
        FileTemaRepo temaRepository = new FileTemaRepo(temaValidator, "D:\\A2S1\\MAP\\Laboratoare\\Lab5\\src\\main\\java/teme.txt");
        FileNotaRepo notaRepository = new FileNotaRepo(notaValidator, "D:\\A2S1\\MAP\\Laboratoare\\Lab5\\src\\main\\java/catalog.txt");
        Service service = new Service(studentRepository, temaRepository, notaRepository);
        UI ui = new UI(service);
        ui.run();
    }
}
