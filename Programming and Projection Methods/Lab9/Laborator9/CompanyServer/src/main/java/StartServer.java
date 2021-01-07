import company.persistance.DBConnection;
import company.server.FirmaDeTransportConfig;
import company.server.Server;
import company.services.IServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.rmi.ServerException;

public class StartServer {
    public static void main(String[] args) {
        /*int defaultPort = 55555;
        ApplicationContext springJavaConfig = new AnnotationConfigApplicationContext(FirmaDeTransportConfig.class);
        IServer server = springJavaConfig.getBean(Server.class);
        AbstractServer appserver = new RpcConcurrentServer(defaultPort, server);
        try {
            appserver.start();
        } catch (ServerException e) {
            System.out.println("Eroare la pornirea serverului\n");
        }*/
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");
        //IServer server = factory.getBean(Server.class);
        //server.
        //DBConnection.closeConnection();
    }
}
