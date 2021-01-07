package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.repository.*;
import sample.service.Service;
import sample.view.AppView;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Monitorizarea temelor de laborator");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        BorderPane notePane=loader.load();
        Controller controller=loader.getController();
        Controller ctrl=loader.getController();
        Service serv=getService();
        controller.setService(serv);
        ctrl.setService(serv);


        AppView appView=new AppView(controller,notePane);
        AppView appView1=new AppView(ctrl,notePane);

        BorderPane borderPane=appView.getView();
        BorderPane borderPane1=appView1.getView();
        borderPane.setId("pane");
        borderPane1.setId("pane");
        Scene scene=new Scene(borderPane,800,550);
        Scene scene1=new Scene(borderPane1,800,550);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        scene1.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        Stage stage=new Stage();
        stage.setTitle("Monitorizarea temelor de laborator");
        stage.setScene(scene1);
        primaryStage.show();
        stage.show();
    }

    static Service getService(){
        FileStudentRepo studentRepository = new FileStudentRepo(new StudentValidator(), "D:\\A2S1\\MAP\\Laboratoare\\Lab10\\src\\sample/studenti.txt");
        FileTemaRepo temaRepository = new FileTemaRepo(new TemaValidator(), "D:\\A2S1\\MAP\\Laboratoare\\Lab10\\src\\sample/teme.txt");
        FileNotaRepo notaRepository = new FileNotaRepo(new NotaValidator(), "D:\\A2S1\\MAP\\Laboratoare\\Lab10\\src\\sample/catalog.txt");
        Service service = new Service(studentRepository, temaRepository, notaRepository);
        return service;
    }

    /*public static void main(String[] args) {
        launch(args);
    }*/
}
