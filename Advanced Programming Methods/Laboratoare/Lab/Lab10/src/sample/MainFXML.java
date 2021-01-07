package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.repository.*;
import sample.service.Service;
import sample.view.AppView;
import sample.view.NoteFXML;

public class MainFXML extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CATALOG");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        BorderPane borderPane=loader.load();
        Controller ctrl=loader.getController();

        ctrl.setService(getService());
        Scene scene=new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static Service getService(){
        FileStudentRepo studentRepository = new FileStudentRepo(new StudentValidator(), "D:\\A2S1\\MAP\\Laboratoare\\Lab\\Lab10\\src\\sample\\studenti.txt");
        FileTemaRepo temaRepository = new FileTemaRepo(new TemaValidator(), "D:\\A2S1\\MAP\\Laboratoare\\Lab\\Lab10\\src\\sample\\teme.txt");
        FileNotaRepo notaRepository = new FileNotaRepo(new NotaValidator(), "D:\\A2S1\\MAP\\Laboratoare\\Lab\\Lab10\\src\\sample\\catalog.txt");
        Service service = new Service(studentRepository, temaRepository, notaRepository);
        return service;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
