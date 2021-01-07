package sample;

import sample.controller.Controller;
import sample.repository.ClientRepo;
import sample.repository.LaptopRepo;
import sample.repository.VanzareRepo;
import sample.service.Service;
import sample.ui.Ui;

import java.time.LocalDate;
import java.util.Date;


public class MainC {
    public static void main(String[] args) {
        LaptopRepo prepo=new LaptopRepo("D:\\A2S1\\MAP\\Exercise\\Laptopuri\\src\\sample/laptops.txt");
        ClientRepo lrepo=new ClientRepo("D:\\A2S1\\MAP\\Exercise\\Laptopuri\\src\\sample/clients.txt");
        VanzareRepo srepo=new VanzareRepo(lrepo,prepo,"D:\\A2S1\\MAP\\Exercise\\Laptopuri\\src\\sample/vanzari.txt");
        Service service=new Service(prepo,lrepo,srepo);
        Controller controller=new Controller();
        controller.setService(service);
        Ui ui=new Ui(controller,args[0]);
        ui.run();
    }
}
