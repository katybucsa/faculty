import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    private List<Pair<String, List<String>>> input = new ArrayList<>();

    public void readFromFile(String file) {

        Elems.stateId = 0;
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split("->");
                String[] right = s[1].split("[ ]");
                List<String> list = Arrays.asList(right);
                input.add(new Pair<>(s[0], list));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pair<String, List<String>>> getInput() {
        return input;
    }

    public List<String> readInputFile(String file) {

        List<String> fileInput = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileInput.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInput;
    }
}