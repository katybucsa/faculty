/**
 * Created on: 09-Dec-19, 23:20
 *
 * @author: Katy Bucșa
 */

public class App {

    public static void main(String[] args) {

        String fileA = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Practic\\Problema2\\src\\resources\\numere.txt";
        String fileC = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Practic\\Problema2\\src\\resources\\coeficienti.txt";
        // String fileIntervale = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Practic\\Problema1\\src\\resources\\interval.txt";
        String path = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Practic\\Problema2\\src\\resources\\";
        new Run().run(fileA, fileC, path);
    }
}