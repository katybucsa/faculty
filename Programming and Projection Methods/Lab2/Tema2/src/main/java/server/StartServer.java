package server;

import controller.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.Service;
import utils.FirmaDeTransportConfig;

import java.rmi.ServerException;


public class StartServer {
    public static void main(String[] args) {
        ApplicationContext springJavaConfig = new AnnotationConfigApplicationContext(FirmaDeTransportConfig.class);
        Service c=springJavaConfig.getBean(Service.class);
        AbstractServer server=new MyConcurrentServer(55555,c);
        try {
            server.start();
        } catch (ServerException e) {
            System.out.println(e.getMessage());
        }
    }
}