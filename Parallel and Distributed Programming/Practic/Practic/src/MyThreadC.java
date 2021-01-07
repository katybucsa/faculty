/**
 * Created on: 10-Dec-19, 16:17
 *
 * @author: Katy Bucșa
 */

public class MyThreadC implements Runnable {

    private int order;

    public MyThreadC(int order) {
        this.order = order;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (Elems.QUEUE) {

                for (int i = 0; i < 3; i++) {
                    while (Elems.QUEUE.isEmpty()) {
                        if (Elems.FINISHED) {
                            return;
                        }
                        try {
                            Elems.QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int elem = Elems.QUEUE.popFirst();
                    synchronized (Elems.out) {
                        Elems.out.println("Sunt consumatorul" + order);
                        Elems.out.println("Elementul primit " + elem);
                        Elems.out.println("Dimensiunea cozii " + Elems.QUEUE.getSize());
                    }
                }
                Elems.QUEUE.notifyAll();
                try {
                    Thread.sleep(8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
