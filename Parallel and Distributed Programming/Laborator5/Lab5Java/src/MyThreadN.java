import monomial.Monomial;

/**
 * Created on: 23-Nov-19, 14:10
 *
 * @author: Katy Bucșa
 */

public class MyThreadN implements Runnable {

    @Override
    public void run() {

        Monomial m;
        while (true) {
            synchronized (Elems.QUEUE) {
                while (Elems.QUEUE.isEmpty()) {
                    try {
                        if (Elems.FINISHED) {
                            return;
                        }
                        Elems.QUEUE.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                m = Elems.QUEUE.remove();
            }
            Elems.lista.aduna_monom(m);
        }
    }
}