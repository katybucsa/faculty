import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;


public class Functions {
    /**
     * @param fileName the name of the file to write data
     * @param size     the number of ints
     * @param min      the minimum number the file may contain
     * @param max      the maximum number the file may contain
     */
    public static void createFile(String fileName, int size, int min, int max) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                out.print(random.nextInt(max - min + 1) + min);
                if (i < size - 1) {
                    out.print(" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file1 first file to check
     * @param file2 second file to check
     * @return true id the 2 files contain the same data
     */
    public static boolean filesEquality(String file1, String file2) {
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
            String line1, line2 ;
            while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {
                String[] s1 = line1.split(" ");
                String[] s2 = line2.split(" ");
                if (line1.length() != line2.length())
                    return false;
                for (int i = 0; i < s1.length; i++) {
                    int number1 = Integer.parseInt(s1[i]);
                    int number2 = Integer.parseInt(s2[i]);
                    if (number1 != number2)
                        return false;
                }
            }
            if ((line1 == null && line2 != null) || (line1 != null && line2 == null))
                return false;
        } catch (FileNotFoundException e) {
            System.out.println("Nu s-a putut deschide fisierul pentru citire!\n");
        } catch (IOException e) {
            System.out.println("Citirea dinfisier a esuat!\n");
        }

        return true;
    }


    /**
     * @param csvFile the name of the csv file where to append the content
     * @param content the information to append to the csv file
     */
    public static void writeToCsvFile(String csvFile, String[] content) {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(csvFile);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, true);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            writer.writeNext(content);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //P1
        Functions.createFile("numbers.txt", 6, -10, 10);

        //P2
        System.out.println(Functions.filesEquality("C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Lab1\\src\\main\\resources\\a.txt", "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Lab1\\src\\main\\resources\\b.txt"));

        //P3
        String[] student = {"Bianca", "21", "Suceava"};
        Functions.writeToCsvFile("c.csv", student);
    }
}
