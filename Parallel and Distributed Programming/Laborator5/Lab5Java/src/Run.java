import pol_generator.PolynomialGenerator;

/**
 * Created on: 14-Nov-19, 18:30
 *
 * @author: Katy Bucșa
 */

public class Run {

    private static void startThreadsL(Thread[] threads, int noThr) {

        //create and start threads
        for (int i = 0; i < noThr; i++) {
            threads[i] = new Thread(new MyThreadL());
            threads[i].start();
        }
    }

    private static void startThreadsN(Thread[] threads, int noThr) {

        //create and start threads
        for (int i = 0; i < noThr; i++) {
            threads[i] = new Thread(new MyThreadN());
            threads[i].start();
        }
    }

    private static void startReaderThreads(Thread[] threads, int noThr, String filePath, int noPol) {

        for (int i = 0; i < noThr; i++) {
            threads[i] = new Thread(new MyReaderThread(filePath, noPol));
            threads[i].start();
        }
    }

    private static void joinThreads(Thread[] threads) {

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void syncList(int noThr, String filePath, int noPol) {
        Thread[] threads = new Thread[noThr];
        startThreadsL(threads, noThr);
        ReadPolynomials.readPolynomialsFromFile(filePath, noPol);
        joinThreads(threads);

        //FileWriter.writeResultToFile(filePath + "v1.txt", 0);

        Elems.syncList.print_list();
    }

    public static void syncNode(int noThr, String filePath, int noPol) {

        Thread[] threads = new Thread[noThr];
        startThreadsN(threads, noThr);
        ReadPolynomials.readPolynomialsFromFile(filePath, noPol);
        joinThreads(threads);

        //FileWriter.writeResultToFile(filePath + "v2.txt", 1);

        //Elems.lista.print_list();
    }

    public static void run(String filePath) {

        int noPol = 4, degree = 4, noThr = 2;
        PolynomialGenerator.generatePolynomials(noPol, degree, filePath);
        long begin1 = System.currentTimeMillis();
        syncList(noThr, filePath, noPol);
        long end1 = System.currentTimeMillis();
        System.out.println();
        System.out.println((end1 - begin1) / 1000.0);

        long begin2 = System.currentTimeMillis();
        syncNode(noThr, filePath, noPol);
        long end2 = System.currentTimeMillis();
        System.out.println();
        System.out.println((end2 - begin2) / 1000.0);
    }
}