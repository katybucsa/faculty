/**
 * Created on: 09-Dec-19, 19:22
 *
 * @author: Katy Bucșa
 */

public class Run {

    private void joinThreads(Thread[] threads) {

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startThreads(Thread[] threads) {

        for (int i = 0; i < threads.length; i++) {
            Thread t = new Thread(new MyThread(i));
            threads[i] = t;
            t.start();
        }
    }

    private void startLastThreads(Thread[] threads, String path) {

        int count = 0;
        int nrC = Elems.INTERVALE.size() / threads.length;
        int r = Elems.INTERVALE.size() % threads.length;

        for (int i = 0; i < threads.length; i++) {
            Runnable myThread;
            if (r > 0) {
                myThread = new MyOtherThread(count, nrC + 1, path);
                count += nrC + 1;
            } else {
                myThread = new MyOtherThread(count, nrC, path);
                count += nrC;
            }
            threads[i] = new Thread(myThread);
            threads[i].start();
            r--;
        }
    }

    private void startThreads2(Thread[] threads) {

        int count = 0;
        int nrC = Elems.BRUIAJE.size() / threads.length;
        int r = Elems.BRUIAJE.size() % threads.length;

        for (int i = 0; i < threads.length; i++) {
            Runnable myThread;
            if (r > 0) {
                myThread = new MyThread2(count, nrC + 1);
                count += nrC + 1;
            } else {
                myThread = new MyThread2(count, nrC);
                count += nrC;
            }
            threads[i] = new Thread(myThread);
            threads[i].start();
            r--;
        }
    }

    private void init(String fileFrecvente, String fileBruiaje, String fileIntervale, String path) {
        Elems.FRECVENTE = FileReader.readFromFile(fileFrecvente);
        Elems.BRUIAJE = FileReader.readFromFile(fileBruiaje);
//        int noThr = Elems.BRUIAJE.size();
//        Elems.MUTEX = new ArrayList<>(Arrays.asList(new Boolean[noThr]));
//        Collections.fill(Elems.MUTEX, Boolean.FALSE);
//        Elems.MUTEX.set(0, true);
//        Thread[] threads = new Thread[noThr];
//        startThreads(threads);
//        joinThreads(threads);
        int noThr = 4;
        Elems.CAN_ACCESS = true;
        Thread[] threads = new Thread[noThr];
        startThreads2(threads);
        synchronized (Elems.CAN_ACCESS) {
            Elems.CAN_ACCESS.notifyAll();
        }
        joinThreads(threads);


        printFrecvente();
//        Elems.INTERVALE = FileReader.readIntervalsFromFile(fileIntervale);
//        threads = new Thread[noThr];
//        startLastThreads(threads, path);
//        joinThreads(threads);
    }

    private void printFrecvente() {

        for (int i = 0; i < Elems.FRECVENTE.size(); i++) {
            System.out.println(Elems.FRECVENTE.get(i));
        }
    }

    public void run(String fileFrecvente, String fileBruiaje, String fileIntervale, String path) {

        init(fileFrecvente, fileBruiaje, fileIntervale, path);
    }
}