/**
 * Created on: 19-Dec-19, 22:49
 *
 * @author: Katy Bucșa
 */

import config.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import persistence.DBConnection;
import persistence.Repo;
import services.IService;
import services.Service;
import services.util.ConcurrentServer;
import services.util.ServerException;

import java.util.Properties;


public class StartServer {

    public static void main(String[] args) {

        int defaultPort = 55555;
        ApplicationContext springJavaConfig = new AnnotationConfigApplicationContext(Configuration.class);
        Repo repo = new Repo(springJavaConfig.getBean(Properties.class));
        IService server = new Service(repo);
        ConcurrentServer concurrentServer = new ConcurrentServer(defaultPort, server);
        try {
            concurrentServer.start();
        } catch (ServerException e) {
            e.printStackTrace();
        }
        DBConnection.closeConnection();
    }
}