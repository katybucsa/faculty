
public class App {

    public static void main(String[] args) {

        String file = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator6\\proiectLFTC\\src\\main\\resources\\inputTest2.txt";
        String fileGram = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator6\\proiectLFTC\\src\\main\\resources\\inputTest2.txt";
        String cerc = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator6\\proiectLFTC\\src\\main\\resources\\fipCerc.txt";
        String suma = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator6\\proiectLFTC\\src\\main\\resources\\fipSuma.txt";
        String cmmdc = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator6\\proiectLFTC\\src\\main\\resources\\fipCmmdc.txt";
        new Run(file).run(cerc, suma, cmmdc,fileGram);
    }
}