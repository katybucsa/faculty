import java.util.Random;

/**
 * Created on: 10-Dec-19, 16:17
 *
 * @author: Katy Bucșa
 */

public class MyThreadP implements Runnable {

    private int order;

    public MyThreadP(int order) {
        this.order = order;
    }

    @Override
    public void run() {

        Random random = new Random();
        for (int k = 0; k < 100; k++) {

            synchronized (Elems.QUEUE) {
                for (int i = 0; i < 4; i++) {
                    while (Elems.QUEUE.isFull()) {
                        try {
                            Elems.QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int elem = random.nextInt();
                    Elems.QUEUE.push(elem);
                    synchronized (Elems.out) {
                        Elems.out.println("Sunt producatorul " + order);
                        Elems.out.println("Am inserat " + elem);
                        Elems.out.println("Dimensiunea cozii " + Elems.QUEUE.getSize());
                    }
                    Elems.QUEUE.notifyAll();
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
