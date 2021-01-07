import monomial.Monomial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created on: 23-Nov-19, 17:17
 *
 * @author: Katy Bucșa
 */

public class MyReaderThread implements Runnable {

    private final int noPol;
    private final String filePath;

    public MyReaderThread(String filePath, int noPol) {

        this.filePath = filePath;
        this.noPol = noPol;
    }

    @Override
    public void run() {

        while (true) {
            Elems.mutex.lock();
            int noFile = Elems.NO_FILE;
            Elems.NO_FILE += 1;
            Elems.mutex.unlock();

            if (noFile > this.noPol) {
                return;
            }

            String file = this.filePath + noFile + ".txt";
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String elems[] = line.split("[,]");
                    synchronized (Elems.QUEUE) {
                        Elems.QUEUE.add(new Monomial(Double.parseDouble(elems[0]), Integer.parseInt(elems[1])));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}