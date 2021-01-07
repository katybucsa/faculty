package sample;

import sample.controller.Controller;
import sample.repository.ClientRepo;
import sample.repository.BookRepo;
import sample.repository.RentRepo;
import sample.service.Service;
import sample.ui.Ui;


public class MainC {
    public static void main(String[] args) {
        BookRepo prepo=new BookRepo("D:\\A2S1\\MAP\\Laboratoare\\Practic\\src\\sample/books.txt");
        ClientRepo lrepo=new ClientRepo("D:\\A2S1\\MAP\\Laboratoare\\Practic\\src\\sample/clients.txt");
        RentRepo srepo=new RentRepo(lrepo,prepo,"D:\\A2S1\\MAP\\Laboratoare\\Practic\\src\\sample/rent.txt");
        Service service=new Service(prepo,lrepo,srepo);
        Controller controller=new Controller();
        controller.setService(service);
        Ui ui=new Ui(controller,args[0]);
        ui.run();

    }
}
