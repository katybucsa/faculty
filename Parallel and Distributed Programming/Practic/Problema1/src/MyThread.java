/**
 * Created on: 09-Dec-19, 22:47
 *
 * @author: Katy Bucșa
 */

public class MyThread implements Runnable {

    private int order;

    public MyThread(int order) {
        this.order = order;
    }

    @Override
    public void run() {

        synchronized (Elems.MUTEX.get(order)) {
            while (!Elems.MUTEX.get(order)) {
                try {
                    Elems.MUTEX.get(order).wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
//            synchronized (Elems.FRECVENTE) {
        for (int j = 0; j < Elems.FRECVENTE.size(); j++) {
            if (Elems.FRECVENTE.get(j) % Elems.BRUIAJE.get(order) == 0) {
                Elems.FRECVENTE.remove(j);
                j--;
            }
        }

        if (order != Elems.BRUIAJE.size() - 1) {
            synchronized (Elems.MUTEX.get(order+1)) {
                Elems.MUTEX.set(order + 1, true);
                //Elems.MUTEX.get(order + 1).notify();
            }
        }
    }
}