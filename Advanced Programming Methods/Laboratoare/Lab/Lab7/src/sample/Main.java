package sample;

import javafx.application.Application;
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
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        BorderPane borderPane=getView();
        borderPane.setId("pane");
        Scene scene=new Scene(borderPane,800,500);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.show();
        primaryStage.setScene(scene);
    }

    static BorderPane getView(){
        FileStudentRepo studentRepository = new FileStudentRepo(new StudentValidator(), "D:\\A2S1\\MAP\\Laboratoare\\Lab\\Lab7\\src\\sample\\studenti.txt");
        FileTemaRepo temaRepository = new FileTemaRepo(new TemaValidator(), "D:\\A2S1\\MAP\\Laboratoare\\Lab\\Lab7\\src\\sample\\teme.txt");
        FileNotaRepo notaRepository = new FileNotaRepo(new NotaValidator(), "D:\\A2S1\\MAP\\Laboratoare\\Lab\\Lab7\\src\\sample\\catalog.txt");
        Service service = new Service(studentRepository, temaRepository, notaRepository);
        Controller controller=new Controller(service);
        AppView studentView=new AppView(controller);
        return studentView.getView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
