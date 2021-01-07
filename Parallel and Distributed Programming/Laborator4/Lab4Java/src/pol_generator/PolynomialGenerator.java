package pol_generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on: 22-Nov-19, 21:31
 *
 * @author: Katy Bucșa
 */

public class PolynomialGenerator {
    public static void generatePolynomial(int degree, String fileName) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            while (degree >= 0) {
                out.println(((int) ((Math.random() * 2 * 9999 + 1)) - 9999) + "," + degree);
                degree--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void generatePolynomials(int noPol, int degree, String filePath) {
        for (int i = 1; i <= noPol; i++) {
            String fileName = filePath + i + ".txt";
            generatePolynomial(degree, fileName);
        }
    }
}
