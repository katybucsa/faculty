import company.client.AppView;
import company.client.Controller;
import company.services.IServer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int defaultPort = 55555;
        /*String defaultServer="localhost";
        IServer serv=new ServerRpcProxy(defaultServer,defaultPort);
        */
        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IServer server = (IServer) factory.getBean("server");
            Controller ctrl = new Controller(server);
            AppView appView = new AppView(ctrl);
            primaryStage.setTitle("Firma de transport - Login");
            BorderPane borderPane = appView.getView();
            Scene scene = new Scene(borderPane, 350, 600);
            scene.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
            primaryStage.setScene(scene);
            appView.setPrimaryStage(primaryStage);
            appView.show();
        }catch(Exception e){
           e.printStackTrace();
        }
    }
}
