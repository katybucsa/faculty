import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on: 10-Dec-19, 13:18
 *
 * @author: Katy Bucșa
 */

public class MyWriterThread implements Runnable {

    private int start;
    private int offset;
    private int order;
    private String filePath;

    public MyWriterThread(int start, int offset, int order, String filePath) {
        this.start = start;
        this.offset = offset;
        this.order = order;
        this.filePath = filePath;
    }

    @Override
    public void run() {

        String fileName = filePath + "rezultat_" + order + ".txt";
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (int i = start; i < start + offset; i++) {
                out.println(Elems.B.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}