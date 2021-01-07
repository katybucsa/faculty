import monomial.Monomial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created on: 22-Nov-19, 18:55
 *
 * @author: Katy Bucșa
 */

public class ReadPolynomials {

    public static void readPolynomialsFromFile(String filePath, int noPol) {

        Elems.FINISHED = false;
        for (int noFile = 1; noFile <= noPol; noFile++) {
            String fileName = filePath + noFile + ".txt";
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String elems[] = line.split("[,]");
                    synchronized (Elems.QUEUE) {
                        Elems.QUEUE.add(new Monomial(Double.parseDouble(elems[0]), Integer.parseInt(elems[1])));
                        Elems.QUEUE.notify();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Elems.FINISHED = true;
        synchronized (Elems.QUEUE) {
            Elems.QUEUE.notifyAll();
        }
    }
}
