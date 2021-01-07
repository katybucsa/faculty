import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class First {

    private Map<String, List<String>> firstTable;


    public First() {
        this.firstTable = new HashMap<>();
    }

    public void init(List<Pair<String, List<String>>> input) {

        boolean changed = true;
        while (changed) {
            changed = false;
            for (Pair<String, List<String>> pair : input) {
                String cheie = pair.getKey();
                List<String> descomp = pair.getValue();
                //String firstLetter = descomp.substring(0, 1);
                String firstWord = descomp.get(0);
                if (Character.isLowerCase(firstWord.charAt(0))) {//prima litera e terminal

                    if (!firstTable.containsKey(cheie)) {

                        List<String> list = new ArrayList<>();
                        list.add(firstWord);        //adaugam cuvantul
                        firstTable.put(cheie, list);
                        changed = true;
                    } else {
                        List<String> terminale = firstTable.get(cheie);
                        if (!terminale.contains(firstWord)) {
                            terminale.add(firstWord);
                            changed = true;
                        }
                    }
                } else {//prima litera e neterminal

                    if (firstTable.containsKey(firstWord)) {
                        List<String> firstWordList = firstTable.get(firstWord);
                        if (!firstTable.containsKey(cheie)) {
                            firstTable.put(cheie, new ArrayList<>());
                        }
                        List<String> currentList = firstTable.get(cheie);
                        for (String l : firstWordList) {
                            if (!currentList.contains(l)) {
                                currentList.add(l);
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public void print(){
        firstTable.forEach((k,v)->{
            System.out.println("Cheie "+k);
            v.forEach(x-> System.out.print(x+" "));
            System.out.println();
        });
    }

    public Map<String, List<String>> getFirstTable() {
        return firstTable;
    }
}