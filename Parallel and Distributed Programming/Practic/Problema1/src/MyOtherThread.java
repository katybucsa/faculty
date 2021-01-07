import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on: 10-Dec-19, 09:53
 *
 * @author: Katy Bucșa
 */

public class MyOtherThread implements Runnable {

    private int start;
    private int offset;
    private String filePath;

    public MyOtherThread(int start, int offset, String filePath) {
        this.start = start;
        this.offset = offset;
        this.filePath = filePath;
    }

    @Override
    public void run() {

        for (int i = start; i < start + offset; i++) {
            String fileName = filePath + "frecventa_" + (i + 1) + ".txt";
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
                Pair<Integer, Integer> p = Elems.INTERVALE.get(i);
                for (int j = 0; j < Elems.FRECVENTE.size(); j++) {
                    if (Elems.FRECVENTE.get(j) >= p.getKey() && Elems.FRECVENTE.get(j) <= p.getValue()) {
                        out.println(Elems.FRECVENTE.get(j));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
