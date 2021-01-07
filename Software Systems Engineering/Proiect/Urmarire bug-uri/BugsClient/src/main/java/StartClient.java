import bugs.client.Controller;
import bugs.client.LoginView;
import bugs.services.IService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class StartClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IService server = (IService) factory.getBean("server");
            Controller ctrl = new Controller(server);
            LoginView loginView = new LoginView(ctrl);
            primaryStage.setTitle("Urmarire bug-uri - Login");
            BorderPane borderPane = loginView.getView();
            Scene scene = new Scene(borderPane, 350, 600);
            scene.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
            primaryStage.setScene(scene);
            loginView.setPrimaryStage(primaryStage);
            loginView.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
