import model.Bilet;
import model.Loc;
import services.IService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created on: 20-Dec-19, 08:27
 *
 * @author: Katy Bucșa
 */

public class Controller {
    private IService server;

    public Controller(IService server) {
        this.server = server;
    }

    public void buyTickets() {

        while (true) {
            try {
                Thread.sleep(5000);
                List<Loc> locuri = server.findAllLocuri();
                AtomicBoolean b = new AtomicBoolean(true);
                locuri.forEach(l -> {
                    if (b.get()) {
                        if (l.getNrLocuriLibere() != 0) {
                            b.set(false);
                            CompletableFuture
                                    .supplyAsync(() -> sendRequest(l.getIdSpectacol(), l.getIdCategorie()))
                                    .thenAccept(this::printResult)
                                    .join();
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printResult(Bilet bilet) {

        System.out.println("Bilet vandut: " + bilet);
    }

    private Bilet sendRequest(int idSpectacol, int idCategorie) {

        Bilet bilet = null;
        try {
            bilet = server.buyTicket(idSpectacol, idCategorie);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return bilet;
    }
}