package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.repository.ClientRepo;
import sample.repository.LocationRepo;
import sample.repository.ReservationRepo;
import sample.service.Service;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Oferte de vacanta");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        BorderPane borderPane=loader.load();
        Controller controller=loader.getController();
        Service serv=getService();
        controller.setService(serv);

        Scene scene=new Scene(borderPane,800,550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static Service getService(){
        LocationRepo prepo=new LocationRepo("D:\\A2S1\\MAP\\Exercise\\Oferte de vacanta\\src\\sample/locations.txt");
        ClientRepo lrepo=new ClientRepo("D:\\A2S1\\MAP\\Exercise\\Oferte de vacanta\\src\\sample/clients.txt");
        ReservationRepo srepo=new ReservationRepo(lrepo,prepo,"D:\\A2S1\\MAP\\Exercise\\Oferte de vacanta\\src\\sample/reservations.txt");
        Service service=new Service(prepo,lrepo,srepo);
        return service;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
