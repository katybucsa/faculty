package sample;

import sample.controller.Controller;

import sample.repository.ClientRepo;
import sample.repository.LocationRepo;
import sample.repository.ReservationRepo;
import sample.service.Service;
import sample.ui.Ui;


public class MainC {
    public static void main(String[] args) {
        LocationRepo prepo=new LocationRepo("D:\\A2S1\\MAP\\Exercise\\Oferte de vacanta\\src\\sample/locations.txt");
        ClientRepo lrepo=new ClientRepo("D:\\A2S1\\MAP\\Exercise\\Oferte de vacanta\\src\\sample/clients.txt");
        ReservationRepo srepo=new ReservationRepo(lrepo,prepo,"D:\\A2S1\\MAP\\Exercise\\Oferte de vacanta\\src\\sample/reservations.txt");
        Service service=new Service(prepo,lrepo,srepo);
        Controller controller=new Controller();
        controller.setService(service);
        Ui ui=new Ui(controller,args[0]);
        ui.run();
    }
}
