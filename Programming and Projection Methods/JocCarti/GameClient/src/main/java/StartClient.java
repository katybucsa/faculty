import game.client.LoginView;
import game.client.Controller;
import game.services.IServer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IServer server = (IServer) factory.getBean("server");
            Controller ctrl = new Controller(server);
            LoginView loginView = new LoginView(ctrl);
            primaryStage.setTitle("Carti - Login");
            BorderPane borderPane = loginView.getView();
            Scene scene = new Scene(borderPane, 350, 600);
            scene.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
            primaryStage.setScene(scene);
            loginView.setPrimaryStage(primaryStage);
            loginView.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
