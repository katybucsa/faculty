package sample;

import sample.controller.Controller;
import sample.repository.LocatieRepo;
import sample.repository.PiesaRepo;
import sample.repository.SpectacolRepo;
import sample.service.Service;
import sample.ui.Ui;

public class MainC {
    public static void main(String[] args) {
        PiesaRepo prepo=new PiesaRepo("D:\\A2S1\\MAP\\Exercise\\Festival\\src\\sample/piese.txt");
        LocatieRepo lrepo=new LocatieRepo("D:\\A2S1\\MAP\\Exercise\\Festival\\src\\sample/locatii.txt");
        SpectacolRepo srepo=new SpectacolRepo(prepo,lrepo,"D:\\A2S1\\MAP\\Exercise\\Festival\\src\\sample/spectacole.txt");
        Service service=new Service(prepo,lrepo,srepo);
        Controller controller=new Controller(service);
        Ui ui=new Ui(controller);
        ui.run();
    }
}
