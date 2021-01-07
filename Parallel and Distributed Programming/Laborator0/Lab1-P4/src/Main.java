import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Main {
    static class RandomNumbers{
        public static void createFile(String fileName, int size, int min, int max){
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
                Random random = new Random();
                for (int i=0;i<size;i++){
                    out.print(random.nextInt(max-min+1)+min);
                    if(i<size-1){
                        out.print(" ");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class FileComparing{
        public static boolean filesEquality(String file1,String file2) {
            Map<Integer, Integer> mapFile1 = new HashMap<>();
            Map<Integer, Integer> mapFile2 = new HashMap<>();

            try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
                 BufferedReader br2 = new BufferedReader(new FileReader(file2))
            ) {
                String line1, line2;
                while ((line1 = br1.readLine()) != null) {
                    String[] s1 = line1.split(" ");
                    for (int i = 0; i < s1.length; i++) {
                        int number1 = Integer.parseInt(s1[i]);
                        if (mapFile1.containsKey(number1)) {
                            mapFile1.put(number1, mapFile1.get(number1) + 1);
                        } else {
                            mapFile1.put(number1, 1);
                        }
                    }
                }

                while ((line2 = br2.readLine()) != null) {
                    String[] s2 = line2.split(" ");
                    for (int j = 0; j < s2.length; j++) {
                        int number2 = Integer.parseInt(s2[j]);
                        if (mapFile2.containsKey(number2)) {
                            mapFile2.put(number2, mapFile2.get(number2) + 1);
                        } else {
                            mapFile2.put(number2, 1);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nu s-a putut deschide fisierul pentru citire!\n");
            } catch (IOException e) {
                System.out.println("Citirea dinfisier a esuat!\n");
            }
            return mapFile1.equals(mapFile2);
        }
    }



    public static void main(String[] args) {
        //P1
        RandomNumbers.createFile("numbers.txt",6,-10,10);

        //P2
        System.out.println(FileComparing.filesEquality("C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Lab1-P4\\src\\a.txt","C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Lab1-P4\\src\\b.txt"));

        //P3
        String[] student = { "Bianca", "21", "Suceava" };
        //FileComparing.writeToCsvFile("c.csv",student);
    }
}
