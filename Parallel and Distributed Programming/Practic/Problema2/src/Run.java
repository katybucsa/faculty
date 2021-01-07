import java.util.ArrayList;
import java.util.Collections;

/**
 * Created on: 10-Dec-19, 12:16
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

        int count = 0;
        int nrC = Elems.A.size() / threads.length;
        int r = Elems.A.size() % threads.length;

        for (int i = 0; i < threads.length; i++) {
            Runnable myThread;
            if (r > 0) {
                myThread = new MyThread(count, nrC + 1);
                count += nrC + 1;
            } else {
                myThread = new MyThread(count, nrC);
                count += nrC;
            }
            threads[i] = new Thread(myThread);
            threads[i].start();
            r--;
        }
    }

    public void startWriterThreads(Thread[] threads, String path) {


        int count = 0;
        int nrC = Elems.B.size() / threads.length;
        int r = Elems.B.size() % threads.length;

        for (int i = 0; i < threads.length; i++) {
            Runnable myThread;
            if (r > 0) {
                myThread = new MyWriterThread(count, nrC + 1, i, path);
                count += nrC + 1;
            } else {
                myThread = new MyWriterThread(count, nrC, i, path);
                count += nrC;
            }
            threads[i] = new Thread(myThread);
            threads[i].start();
            r--;
        }
    }

    public void run(String fileA, String fileC, String path) {

        Elems.A = FileReader.readFromFile(fileA);
        Elems.C = FileReader.readFromFile(fileC);
        Elems.B = new ArrayList<>(Collections.nCopies(Elems.A.size(), 0));

        int noThr = 4;
        Thread[] threads = new Thread[noThr];
        startThreads(threads);
        joinThreads(threads);

        Collections.sort(Elems.B);
        for (int i = 0; i < Elems.B.size(); i++) {
            System.out.print(Elems.B.get(i) + " ");
        }

        threads = new Thread[noThr];
        startWriterThreads(threads, path);
        joinThreads(threads);
    }
}