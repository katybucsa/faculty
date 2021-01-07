import javafx.util.Pair;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {

    private List<Stare> states;
    private List<Pair<String, List<String>>> input;
    private MultiValuedMap<Stare, Map<String, List<String>>> table;
    private boolean isLR1 = true;

    public Table(List<Stare> states, List<Pair<String, List<String>>> input) {
        this.states = states;
        this.input = input;
        this.table = new ArrayListValuedHashMap<>();
        initTable();
    }


    private void initTable() {

        states.forEach(state -> {

            if (state.getTranzitii().size() != state.getProductii().size()) {

                state.getTranzitii().forEach((s, id) -> {
                    List<String> strs = determineActions(state, id);
                    Map<String, List<String>> m = new HashMap<>();
                    m.put(s, strs);
                    table.put(state, m);
                    if (strs.size() > 1) {
                        this.isLR1 = false;
                    }
                });

                state.getProductii().asMap().forEach((k, v) -> {
                    v.forEach(pair -> {
                        List<String> strs = new ArrayList<>();
                        if (pair.getKey().get(pair.getKey().size() - 1).equals(".")) {
                            int ruleNumber = getRuleNumber(k, pair.getKey().subList(0, pair.getKey().size() - 1));
                            String r;
                            if (ruleNumber != 0) {
                                r = "r" + ruleNumber;
                            } else {
                                r = "acc";
                            }
                            List<String> words = pair.getValue();
                            strs.add(r);
                            for (String word : words) {
                                Map<String, List<String>> m = new HashMap<>();
                                m.put(word, strs);
                                table.put(state, m);
                            }
                        }
                    });
                });
            } else {
                state.getTranzitii().forEach((s, id) -> {
                    List<String> strs = detActions(state, id);
                    Map<String, List<String>> m = new HashMap<>();
                    m.put(s, strs);
                    table.put(state, m);
                    if (strs.size() > 1) {
                        this.isLR1 = false;
                    }
                });
                if (state.getTranzitii().size() == 0) {
                    List<String> strs = detActions(state);
                    state.getProductii().asMap().forEach((k, v) -> {
                        v.forEach(pair -> {
                            List<String> words = pair.getValue();
                            for (int i = 0; i < words.size(); i++) {
                                Map<String, List<String>> m = new HashMap<>();
                                m.put(words.get(i), strs);
                                table.put(state, m);
                            }
                        });
                    });
                    if (strs.size() > 1) {
                        this.isLR1 = false;
                    }
                }
            }
        });
    }

    private List<String> determineActions(Stare stare, int nextStateId) {

        List<String> strs = new ArrayList<>();

        stare.getProductii().asMap().forEach((k, v) -> {
            v.forEach(pair -> {
                if (!pair.getKey().get(pair.getKey().size() - 1).equals(".")) {
                    if (!strs.contains("s" + nextStateId)) {
                        strs.add("s" + nextStateId);
                    }
                }
            });
        });
        return strs;
    }

    private List<String> detActions(Stare stare, Integer... nextStateId) {

        List<String> strs = new ArrayList<>();
        final int id;
        if (nextStateId.length > 0) {
            id = nextStateId[0];
        } else {
            id = -1;
        }
        //int id = nextStateId.length > 0 ? nextStateId[0] : -1;
        stare.getProductii().asMap().forEach((k, v) -> {
            v.forEach(pair -> {
                if (pair.getKey().get(pair.getKey().size() - 1).equals(".")) {
                    int ruleNo = getRuleNumber(k, pair.getKey().subList(0, pair.getKey().size() - 1));
                    String r;
                    if (ruleNo == 0) {
                        r = "acc";
                    } else {
                        r = "r" + ruleNo;
                    }
                    strs.add(r);
                } else {
                    int id1;
                    if (id == -1) {
                        id1 = stare.getStateId();
                    } else {
                        id1 = id;
                    }
                    if (!strs.contains("s" + id1)) {
                        strs.add("s" + id1);
                    }
                }
            });
        });
        return strs;
    }

    private int getRuleNumber(String left, List<String> right) {

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).getKey().equals(left) && input.get(i).getValue().equals(right)) {
                return i;
            }
        }
        return 0;
    }

    public MultiValuedMap<Stare, Map<String, List<String>>> getTable() {
        return table;
    }

    public void print() {
        table.asMap().forEach((k, v) -> {
            System.out.println("Stare: " + k.getStateId());
            v.forEach(x -> {
                x.forEach((kk, vv) -> {
                    System.out.println("Litera: " + kk + ", Actiune: " + vv);
                });
            });
            System.out.println();
        });
    }
}