import javafx.util.Pair;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.*;

public class Run {

    private Utils utils;
    private First first;
    private Tranzitii tranzitii;
    private Table table;
    private Checker checker;
    private List<Pair<String, List<String>>> input;
    private Map<String, List<String>> firstTable;
    private List<Stare> stari;
    private MultiValuedMap<Stare, Map<String, List<String>>> actionTable;
    private String file;

    public Run(String file) {
        this.file = file;
        init(this.file);
    }

    private void init(String file) {

        this.utils = new Utils();
        utils.readFromFile(file);
        this.input = utils.getInput();
        this.first = new First();
        first.init(input);
        this.firstTable = first.getFirstTable();
        this.tranzitii = new Tranzitii(firstTable, input);
        this.stari = tranzitii.getStari();
        this.table = new Table(stari, input);
        this.actionTable = table.getTable();
        this.checker = new Checker(input, actionTable);
    }

    private void printFirstTable() {

        firstTable.forEach((k, v) -> {
            System.out.print(k + ": ");
            v.forEach(x -> System.out.print(x + ","));
            System.out.println();
        });
    }

    private void printStates() {

        stari.forEach(System.out::println);
    }

    private void printActionTable() {

        table.print();
    }

    private void checkIfSequenceIsAccepted() {

        init(this.file);
        System.out.println("Introduceti secventa: ");
        String sequence = new Scanner(System.in).nextLine();
        String[] arr = sequence.split("[ ]");
        List<String> seq = new ArrayList<>(Arrays.asList(arr));
        System.out.println(seq);
//        seq.add("inceput");
//        seq.add("real");
//        seq.add("id");
//        seq.add("v");
//        seq.add("id");
//        seq.add("v");
//        seq.add("id");
//        seq.add("pv");
//        seq.add("introdu");
//        seq.add("pd");
//        seq.add("id");
//        seq.add("pi");
//        seq.add("pv");
//        seq.add("id");
//        seq.add("egal");
//        seq.add("const");
//        seq.add("ori");
//        seq.add("const");
//        seq.add("ori");
//        seq.add("id");
//        seq.add("pv");
//        seq.add("id");
//        seq.add("egal");
//        seq.add("const");
//        seq.add("ori");
//        seq.add("id");
//        seq.add("ori");
//        seq.add("id");
//        seq.add("pv");
//        seq.add("tipareste");
//        seq.add("pd");
//        seq.add("id");
//        seq.add("pi");
//        seq.add("pv");
//        seq.add("tipareste");
//        seq.add("pd");
//        seq.add("id");
//        seq.add("pi");
//        seq.add("pv");
//        seq.add("sfarsit");
        Stack<Integer> productionString = checker.getProd(seq);
        System.out.print("Sirul productiilor este: ");
        productionString.forEach(System.out::print);
    }

    private void checkFile(String file,String fileGram) {

        init(fileGram);
        List<String> fileInput = utils.readInputFile(file);
        Stack<Integer> productionString = checker.getProd(fileInput);
        System.out.print("Sirul productiilor este: ");
        productionString.forEach(System.out::print);
    }

    public void run(String cerc, String suma, String cmmdc, String fileGram) {

        String cmd;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n0. Exit");
            System.out.println("1. Pentru a afisa tabelul de first");
            System.out.println("2. Pentru a afisa multimea starilor");
            System.out.println("3. Pentru a afisa tabelul de actiuni");
            System.out.println("4. Pentru a verifica daca o secventa este acceptata");
            System.out.println("5. Pentru a verifica fisierul cerc.txt");
//            System.out.println("6. Pentru a verifica fisierul suma.txt");
//            System.out.println("7. Pentru a verifica fisierul cmmdc.txt\n");
            System.out.println("Introduceti comanda:\n");
            cmd = scanner.next();
            try {
            switch (cmd) {
                case "0":
                    return;
                case "1":
                    printFirstTable();
                    break;
                case "2":
                    printStates();
                    break;
                case "3":
                    printActionTable();
                    break;
                case "4":
                    checkIfSequenceIsAccepted();
                    break;
                case "5":
                    checkFile(cerc,fileGram);
                    break;
//                case "6":
//                    checkFile(suma,fileGram);
//                    break;
//                case "7":
//                    checkFile(cmmdc,fileGram);
//                    break;
                default:
                    System.out.println("Comanda invalida! Reintroduceti comanda.\n");
                    break;
            }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}