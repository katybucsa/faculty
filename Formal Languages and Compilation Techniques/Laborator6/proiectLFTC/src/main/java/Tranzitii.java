import javafx.util.Pair;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Tranzitii {

    private List<Stare> stari;
    private Map<String, List<String>> firstTable;
    private List<Pair<String, List<String>>> input;

    public Tranzitii(Map<String, List<String>> firstTable, List<Pair<String, List<String>>> input) {

        stari = new ArrayList<>();
        this.firstTable = firstTable;
        this.input = input;
        init();
    }


    private MultiValuedMap<String, MutablePair<List<String>, List<String>>> getProductionsFromRule(MultiValuedMap<String, MutablePair<List<String>, List<String>>> productii) {

        AtomicBoolean changed = new AtomicBoolean(true);
        List<String> reguliAdaugate = new ArrayList<>();
        while (changed.get()) {
            changed.set(false);
            MultiValuedMap<String, MutablePair<List<String>, List<String>>> productiiAux = new ArrayListValuedHashMap<>();
            productii.asMap().forEach((k, v) -> {//productii
                v.forEach(pair -> {//parcurgem partea dreapta
                    List<String> descompunere = pair.getKey(); //luam partea cu .
                    int index = descompunere.indexOf(".");
                    if (index != descompunere.size() - 1) { //mai trebuie descompusa
                        if (Character.isUpperCase(descompunere.get(index + 1).charAt(0))) {//verif daca dupa . avem neterminal
                            String neterminal = descompunere.get(index + 1);
                            if (!reguliAdaugate.contains(neterminal)) {//verif daca regulile au fost deja descompuse
                                reguliAdaugate.add(neterminal);
                                input.forEach(regula -> {
                                    if (regula.getKey().equals(neterminal)) { //cautam neterminalul in input
                                        List<String> conc = new ArrayList<>();
                                        conc.add(".");
                                        conc.addAll(regula.getValue());
                                        List<String> predictii = new ArrayList<>();
                                        productii.asMap().forEach((k1, v1) -> {//se cauta toate predictiile pentru o noua regula
                                            // => se cauta toate productiile care au . in fata neterminalului

                                            v1.forEach(pair1 -> {
                                                int punctIdx = pair1.getKey().indexOf(".");
                                                if (punctIdx != pair1.getKey().size() - 1 && pair1.getKey().get(punctIdx + 1).equals(neterminal)) {

                                                    if (punctIdx + 1 == pair1.getKey().size() - 1) { //verif daca dupa neterminal nu exista niciun caracter
//
                                                        predictii.addAll(pair1.getValue());
                                                    } else {//exista un caracter dupa .neterminal
                                                        String secondWord = pair1.getKey().get(punctIdx + 2);
                                                        if (Character.isUpperCase(secondWord.charAt(0))) {//verif daca caract e neterminal
                                                            //lista first pentru neterminal
                                                            predictii.addAll(firstTable.get(secondWord));
//
                                                        } else {//elementul de dupa.neterminal e litera mica=>
                                                            //=>punem doar acel element
                                                            predictii.add(secondWord);
                                                        }
                                                    }
                                                }
                                            });
                                        });
                                        productiiAux.put(neterminal, new MutablePair<>(conc, predictii));
                                        changed.set(true);
                                    }
                                });
                            }
                        }
                    }
                });
            });
            productiiAux.asMap().forEach((k, v) ->
                    v.forEach(x ->
                            productii.put(k, x))
            );
        }
        return productii;
    }

    private int createNewState(MultiValuedMap<String, MutablePair<List<String>, List<String>>> productii, String elem, int stateId) {

        MultiValuedMap<String, MutablePair<List<String>, List<String>>> allProductions = getProductionsFromRule(deepCopyMap(productii));
        int x = alreadyExistingState(allProductions);
        if (x != -1) {
            Elems.stateId--;
            return x;
        }
        Stare state = new Stare(stateId, elem, allProductions, null);
        stari.add(state);
        Map<String, Integer> tranzitii = new HashMap<>();
        List<String> elementeDeDupaPunct = new ArrayList<>();
        allProductions.asMap().forEach((k, v) -> {
            v.forEach(pair -> {
                MultiValuedMap<String, MutablePair<List<String>, List<String>>> map = new ArrayListValuedHashMap<>();
                if (pair.getKey().indexOf(".") != pair.getKey().size() - 1) {
                    String elemTranzitie = pair.getKey().get(pair.getKey().indexOf(".") + 1);
                    allProductions.asMap().forEach((k1, v1) -> {
                        v1.forEach(pair1 -> {
                            if (pair1.getKey().indexOf(".") != pair1.getKey().size() - 1) {
                                if (elemTranzitie.equals(pair1.getKey().get(pair1.getKey().indexOf(".") + 1))) {
                                    map.put(k1, pair1);
                                }
                            }
                        });
                    });
                    if (!elementeDeDupaPunct.contains(elemTranzitie)) {
                        elementeDeDupaPunct.add(elemTranzitie);
                        int id = Elems.stateId;
                        tranzitii.put(elemTranzitie, id);
                        int newId = descompuneRegulaProdutie(deepCopyMap(map), elemTranzitie, Elems.stateId++);
                        if (newId != -1) {
                            tranzitii.put(elemTranzitie, newId);
                        }
                    }
                }
            });
        });

        state.setTranzitii(tranzitii);
        return x;
    }

    private int alreadyExistingState(MultiValuedMap<String, MutablePair<List<String>, List<String>>> productions) {

        AtomicInteger b = new AtomicInteger(-1);
        stari.forEach(s -> {
            if (s.getProductii().equals(productions)) {
                b.set(s.getStateId());
            }
        });
        return b.get();
    }

    private void init() {

        MultiValuedMap<String, MutablePair<List<String>, List<String>>> productii = new ArrayListValuedHashMap<>();
        //String concatenat = "." + input.get(0).getValue();
        List<String> concatenat = new ArrayList<>();
        concatenat.add(".");
        concatenat.addAll(input.get(0).getValue());
        List<String> dolar = new ArrayList<>();
        dolar.add("$");
        productii.put(input.get(0).getKey(), new MutablePair<>(concatenat, dolar));

        createNewState(deepCopyMap(productii), "", Elems.stateId++);
    }

    private MultiValuedMap<String, MutablePair<List<String>, List<String>>> deepCopyMap(MultiValuedMap<String, MutablePair<List<String>, List<String>>> map) {

        MultiValuedMap<String, MutablePair<List<String>, List<String>>> newMap = new ArrayListValuedHashMap<>();
        map.asMap().forEach((k, v) -> {
            v.forEach(pair -> {
                List<String> key = new ArrayList<>();
                List<String> value = new ArrayList<>();
                pair.getKey().forEach(key::add);
                pair.getValue().forEach(value::add);
                newMap.put(k, new MutablePair<>(key, value));
            });
        });
        return newMap;
    }

    private int descompuneRegulaProdutie(MultiValuedMap<String, MutablePair<List<String>, List<String>>> reguli, String elemTranzitie, int currentId) {

        //poti avea mai multe reguli de productie la inceput pe care sa le descompui
        reguli.asMap().forEach((k, v) -> {
            v.forEach(pair -> {
                int pozPunct = pair.getKey().indexOf(".");
                if (pozPunct != pair.getKey().size() - 1) {
                    List<String> descompunere = pair.getKey();
                    String aux = descompunere.get(pozPunct);//luam elementul care contine punctul
                    descompunere.set(pozPunct, descompunere.get(pozPunct + 1));
                    descompunere.set(pozPunct + 1, aux);
                }
            });
        });
        return createNewState(deepCopyMap(reguli), elemTranzitie, currentId);
    }

    public List<Stare> getStari() {
        return stari;
    }
}