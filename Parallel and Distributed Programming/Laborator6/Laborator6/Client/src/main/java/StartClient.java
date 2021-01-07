import services.IService;
import services.util.ServerProxy;

/**
 * Created on: 20-Dec-19, 08:36
 *
 * @author: Katy Bucșa
 */

public class StartClient {

    public static void main(String[] args) {

        int defaultPort = 55555;
        String defaultServer = "localhost";
        IService server = new ServerProxy(defaultServer, defaultPort);
        Controller controller = new Controller(server);
        controller.buyTickets();
    }
}
