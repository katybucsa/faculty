/**
 * Created on: 09-Dec-19, 23:20
 *
 * @author: Katy Bucșa
 */

public class App {

    public static void main(String[] args) {

        String fileFrecvente = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Practic\\Problema1\\src\\resources\\semnale_in.txt";
        String fileBruiaje = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Practic\\Problema1\\src\\resources\\bruiaj.txt";
        String fileIntervale = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Practic\\Problema1\\src\\resources\\interval.txt";
        String path = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Practic\\Problema1\\src\\resources\\";
        new Run().run(fileFrecvente, fileBruiaje, fileIntervale, path);
    }
}