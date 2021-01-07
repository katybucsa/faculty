import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created on: 16-Oct-19, 22:19
 *
 * @author: Katy Bucșa
 */
class BigIntegerGenerator {
    public static void genereateBigInt(int nrC, String fileName) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            Random random = new Random();
            int c;
            while (nrC > 1) {
                out.print(random.nextInt(10));
                nrC--;
            }
            while ((c = random.nextInt(10)) == 0) {
                continue;
            }
            out.print(c);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
