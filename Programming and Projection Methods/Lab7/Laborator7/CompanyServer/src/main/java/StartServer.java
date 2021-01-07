import company.persistance.DBConnection;
import company.server.FirmaDeTransportConfig;
import company.server.Server;
import company.services.IServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import utils.AbstractServer;
import utils.RpcConcurrentServer;

import java.rmi.ServerException;

public class StartServer {
    public static void main(String[] args) {
        int defaultPort = 55555;
        ApplicationContext springJavaConfig = new AnnotationConfigApplicationContext(FirmaDeTransportConfig.class);
        IServer server = springJavaConfig.getBean(Server.class);
        AbstractServer appserver = new RpcConcurrentServer(defaultPort, server);
        try {
            appserver.start();
        } catch (ServerException e) {
            System.out.println("Eroare la pornirea serverului\n");
        }
        DBConnection.closeConnection();
    }
}
