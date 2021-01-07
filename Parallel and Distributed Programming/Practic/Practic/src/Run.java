import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created on: 10-Dec-19, 16:55
 *
 * @author: Katy Bucșa
 */

public class Run {

    public void startCThreads(Thread[] threads) {

        for (int i = 0; i < threads.length; i++) {
            Thread t = new Thread(new MyThreadC(i));
            threads[i] = t;
            t.start();
        }
    }

    public void startPThreads(Thread[] threads) {

        for (int i = 0; i < threads.length; i++) {
            Thread t = new Thread(new MyThreadP(i));
            threads[i] = t;
            t.start();
        }
    }

    private void joinThreads(Thread[] threads) {

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void run(String file) {

        Scanner scanner = new Scanner(System.in);
        Elems.C = scanner.nextInt();
        Elems.P = scanner.nextInt();
        Elems.n = scanner.nextInt();
        try {
            Elems.out = new PrintWriter(new FileWriter(file, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread[] threadsC = new Thread[Elems.C];
        Thread[] threadsP = new Thread[Elems.P];
        startCThreads(threadsC);
        startPThreads(threadsP);
        joinThreads(threadsP);
        Elems.FINISHED = true;
        synchronized (Elems.QUEUE) {
            Elems.QUEUE.notifyAll();
        }
        joinThreads(threadsC);
        Elems.out.close();

    }
}
