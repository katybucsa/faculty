import node.INode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created on: 23-Nov-19, 18:34
 *
 * @author: Katy Bucșa
 */

public class FileWriter {

    public static void writeResultToFile(String file, int k) {

        try (PrintWriter pw = new PrintWriter(file)) {
            INode curent;
            if (k == 0) {
                curent = Elems.syncList.getRoot();
            } else {
                curent = Elems.lista.getRoot();
            }
            while (curent != null) {
                if (curent.getExponent() == 1) {
                    pw.print(curent.getCoeficient() + " X");
                } else if (curent.getExponent() == 0) {
                    pw.print(curent.getCoeficient());
                } else {
                    pw.print(curent.getCoeficient() + " X^" + curent.getExponent());
                }
                curent = curent.getNext();
                if (curent != null) {
                    pw.print(" + ");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}