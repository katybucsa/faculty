import monomial.Monomial;

public class MyThreadL implements Runnable {

    @Override
    public void run() {

        Monomial m;
        while (true) {
            synchronized (Elems.QUEUE) {
                while (Elems.QUEUE.isEmpty()) {
                    if (Elems.FINISHED) {//elements are all added to result
                        return;
                    }
                    try {
                        Elems.QUEUE.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                m = Elems.QUEUE.remove();
            }
            Elems.syncList.aduna_monom(m);
        }
    }
}
