/**
 * Created on: 10-Dec-19, 10:58
 *
 * @author: Katy Bucșa
 */

public class MyThread2 implements Runnable {

    private int start;
    private int offset;

    public MyThread2(int start, int offset) {
        this.start = start;
        this.offset = offset;
    }

    @Override
    public void run() {

        synchronized (Elems.CAN_ACCESS) {

            while (!Elems.CAN_ACCESS) {
                try {
                    Elems.CAN_ACCESS.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = start; i < start + offset; i++) {
            for (int j = 0; j < Elems.FRECVENTE.size(); j++) {
                if (Elems.FRECVENTE.get(j) % Elems.BRUIAJE.get(i) == 0) {
                    Elems.FRECVENTE.remove(j);
                    j--;
                }
            }
        }
        Elems.CAN_ACCESS = true;
    }
}
