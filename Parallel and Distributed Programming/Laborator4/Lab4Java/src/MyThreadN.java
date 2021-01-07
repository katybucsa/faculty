import monomial.Monomial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created on: 23-Nov-19, 14:10
 *
 * @author: Katy Bucșa
 */

public class MyThreadN implements Runnable {

    private final int offset;
    private final int begin;
    private final String filePath;

    public MyThreadN(String filePath, int begin, int offset) {
        this.filePath = filePath;
        this.begin = begin;
        this.offset = offset;
    }

    @Override
    public void run() {

        for (int noFile = begin; noFile < begin + offset; noFile++) {
            String fileName = filePath + noFile + ".txt";
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String elems[] = line.split("[,]");
                    Elems.lista.aduna_monom(new Monomial(Double.parseDouble(elems[0]), Integer.parseInt(elems[1])));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}