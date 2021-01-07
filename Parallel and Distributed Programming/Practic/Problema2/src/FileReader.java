import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<Integer> readFromFile(String file) {

        List<Integer> elems = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("[,]");
                for (String string : strings) {
                    elems.add(Integer.parseInt(string));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elems;
    }

//    public static List<Pair<Integer, Integer>> readIntervalsFromFile(String file) {
//
//        List<Pair<Integer, Integer>> list = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] s = line.split("[,]");
//                list.add(new Pair<>(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}