import company.server.FirmaDeTransportConfig;
import company.server.Server;
import company.services.IServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartServer {
    public static void main(String[] args) {
        try {
            ApplicationContext springJavaConfig = new AnnotationConfigApplicationContext(FirmaDeTransportConfig.class);
            Server server = springJavaConfig.getBean(Server.class);
            IServer.Processor processor = new IServer.Processor(server);

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handleReq(processor);
                }
            };

            new Thread(runnable).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleReq(IServer.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting serving clients...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
