import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import utils.DBConnection;
import utils.FirmaDeTransportConfig;
import view.AppView;

import java.awt.*;

public class MainGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Firma de transport");
        ApplicationContext springJavaConfig = new AnnotationConfigApplicationContext(FirmaDeTransportConfig.class);
        Controller c=springJavaConfig.getBean(Controller.class);
        //AppView appView = springJavaConfig.getBean(AppView.class);
        AppView appView=new AppView(c);
        BorderPane borderPane = appView.getView();
        Scene scene=new Scene(borderPane,350,600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        appView.setPrimaryStage(primaryStage);
        appView.show();
        DBConnection.closeConnection();
        //primaryStage.show();*/
    }
}
