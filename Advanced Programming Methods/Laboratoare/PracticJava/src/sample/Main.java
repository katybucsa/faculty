package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.repository.ClientRepo;
import sample.repository.BookRepo;
import sample.repository.RentRepo;
import sample.service.Service;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Books");
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
        BookRepo prepo=new BookRepo("D:\\A2S1\\MAP\\Laboratoare\\Practic\\src\\sample/books.txt");
        ClientRepo lrepo=new ClientRepo("D:\\A2S1\\MAP\\Laboratoare\\Practic\\src\\sample/clients.txt");
        RentRepo srepo=new RentRepo(lrepo,prepo,"D:\\A2S1\\MAP\\Laboratoare\\Practic\\src\\sample/rent.txt");
        Service service=new Service(prepo,lrepo,srepo);
        return service;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
