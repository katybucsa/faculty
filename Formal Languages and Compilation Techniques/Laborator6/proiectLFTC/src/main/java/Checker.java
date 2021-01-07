import javafx.util.Pair;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Checker {

    private List<Pair<String, List<String>>> input;
    private MultiValuedMap<Stare, Map<String, List<String>>> table;


    public Checker(List<Pair<String, List<String>>> input, MultiValuedMap<Stare, Map<String, List<String>>> table) {
        this.input = input;
        this.table = table;
    }

    public Stack<Integer> getProd(List<String> inputSequence) {

        List<String> l = new ArrayList<>();
        l.add("$");
        l.add("0");
        inputSequence.add("$");
        return check(l, inputSequence, new Stack<>());
    }


    private Stare getStare(int id) {

        AtomicReference<Stare> s = new AtomicReference<>();
        table.asMap().forEach((stare, v) -> {
            if (stare.getStateId() == id) {
                s.set(stare);
            }
        });
        return s.get();
    }

    private Stack<Integer> check(List<String> workingStack, List<String> inputSequence, Stack<Integer> outputStack) {

        int last = Integer.parseInt(workingStack.get(workingStack.size() - 1));
        AtomicBoolean finish = new AtomicBoolean(false);
        AtomicBoolean error = new AtomicBoolean(false);
        AtomicBoolean containsKey = new AtomicBoolean(false);

        table.asMap().forEach((k, v) -> {
            if (k.getStateId() == last) {
                v.forEach(x -> {
                    if (x.containsKey(inputSequence.get(0))) {
                        containsKey.set(true);
                    }
                    x.forEach((kk, vv) -> {
                        if (inputSequence.get(0).equals(kk)) {
                            if (vv.size() == 0) {
                                error.set(true);
                            } else {
                                if (kk.equals("$")
                                        && inputSequence.get(0).equals("$")
                                        && vv.get(0).equals("acc")) {
                                    finish.set(true);
                                } else if (!inputSequence.get(0).equals("$")
                                        && vv.get(0).equals("acc")
                                        && inputSequence.get(0).equals(kk)) {
                                    error.set(true);
                                }
                            }
                        }
                    });
                });
            }
        });
        if (finish.get())
            return outputStack;
        if (error.get() || !containsKey.get())
            throw new RuntimeException("Secventa nu este acceptata!\n");

        String first = inputSequence.get(0);
        AtomicReference<List<String>> workingStackAux = new AtomicReference<>(workingStack);
        AtomicReference<List<String>> inputSequenceAux = new AtomicReference<>(inputSequence);
        AtomicReference<Stack<Integer>> outputStackAux = new AtomicReference<>(outputStack);
        table.asMap().forEach((k, v) -> {
            if (k.getStateId() == last) {
                v.forEach(x -> {
                    x.forEach((kk, vv) -> {
                        if (kk.equals(first)) {
                            String action = vv.get(0);
                            if (action.substring(0, 1).equals("s")) {//daca trebuie sa facem shift
                                List<String> aux = workingStackAux.get();//ce avem in workingStack
                                aux.add(first);//modificat
                                aux.add(action.substring(1));//modificat
                                workingStackAux.set(aux);
                                if (inputSequenceAux.get().size() != 1) { // -1
                                    List<String> auxS = inputSequenceAux.get().subList(1, inputSequenceAux.get().size());
                                    inputSequenceAux.set(auxS);
                                }
                            } else { // daca e reducere
                                int ruleNumber = Integer.parseInt(action.substring(1)); // daca are mai multe cifre actiunea
                                Pair<String, List<String>> rule = input.get(ruleNumber);
                                List<String> ruleValueAux = rule.getValue();
                                int idx = workingStackAux.get().size() - 1;
                                while (!ruleValueAux.isEmpty()) {
                                    if (workingStackAux.get().get(idx).equals(ruleValueAux.get(ruleValueAux.size() - 1))) {
                                        ruleValueAux = ruleValueAux.subList(0, ruleValueAux.size() - 1);
                                    }
                                    idx -= 1;
                                }
                                List<String> aux = workingStackAux.get().subList(0, idx + 1);
                                String lastDigit = aux.get(aux.size() - 1);
                                Stare stare = getStare(Integer.parseInt(lastDigit));
                                aux.add(rule.getKey());//am pus litera din regula de productie
                                AtomicReference<String> lastDigitFromTable = new AtomicReference<>();
                                table.asMap().get(stare).forEach(x1 -> {
                                    x1.forEach((k1, v1) -> {
                                        if (k1.equals(rule.getKey())) {
                                            lastDigitFromTable.set(v1.get(0).substring(1));
                                        }
                                    });
                                });
                                aux.add(lastDigitFromTable.toString());
                                workingStackAux.set(aux);

                                Stack<Integer> stack = new Stack<>();
                                stack.push(ruleNumber);
                                outputStackAux.get().forEach(y -> stack.push(y));
                                //Collections.reverse(stack);
                                outputStackAux.set(stack);
                            }
                        }
                    });
                });
            }
        });
        return check(workingStackAux.get(), inputSequenceAux.get(), outputStackAux.get());
    }
}