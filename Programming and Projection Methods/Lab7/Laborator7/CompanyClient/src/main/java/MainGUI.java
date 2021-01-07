import company.client.AppView;
import company.client.Controller;
import company.services.IServer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import rpcprotocol.ServerRpcProxy;

public class MainGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int defaultPort=55555;
        String defaultServer="localhost";
        IServer serv=new ServerRpcProxy(defaultServer,defaultPort);
        Controller ctrl=new Controller(serv);
        AppView appView=new AppView(ctrl);
        primaryStage.setTitle("Firma de transport - Login");
        BorderPane borderPane = appView.getView();
        Scene scene=new Scene(borderPane,350,600);
        scene.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        appView.setPrimaryStage(primaryStage);
        appView.show();

    }
}
