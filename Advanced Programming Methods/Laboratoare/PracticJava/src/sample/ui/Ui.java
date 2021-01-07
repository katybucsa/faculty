package sample.ui;

import com.sun.source.tree.LambdaExpressionTree;
import sample.controller.Controller;
import sample.domain.Book;
import sample.domain.Client;
import sample.domain.Type;

import java.util.*;
import java.util.stream.Collectors;

public class Ui {
    private Controller controller;
    private String argument;

    public Ui(Controller controller, String argument) {
        this.controller = controller;
        this.argument=argument;
    }

    private void printMeniu() {
        System.out.println("1. Afisati lista locatiilor dintr-o anumita zona(location) specificate ca parametru in linia de comanda\n" +
                        "2. Afisati nr de rezervari din anul curent grupate dupa client\n" +
                        "3. Afisati suma incasata de fiecare hotel pentru rezervarile facute\n");
        System.out.println("0.  Exit");
    }

    /**
     * @param mesaj - message to be printed on the screen
     * @return the int read from keyboard
     */
    private int readInt(String mesaj) {
        System.out.println(mesaj);
        return new Scanner(System.in).nextInt();
    }

    /**
     * @param mesaj -message to be printed on the screen
     * @return the String read from keyboard
     */
    private String readString(String mesaj) {
        System.out.println(mesaj);
        return new Scanner(System.in).nextLine();
    }

    private void execute(int cmd) {

        if (cmd == 1)
            showBooksByType();
        else if(cmd==2)
            showNrCarti();
        else if(cmd==3)
            showInchirieri();
        else
            System.out.println("Comanda invalida\n");
    }

    private void showBooksByType(){
        for(Map.Entry<Type, List<Book>> entry:controller.getBooksByType().entrySet()){
            System.out.println("Tipul: "+entry.getKey());
            for(Book b:entry.getValue().stream().sorted(Comparator.comparing(Book::getAuthor).reversed()).collect(Collectors.toList())){
                System.out.println("\t\t"+b);
            }
        }
    }

    private void showNrCarti(){
        System.out.println("Numarul de carti inchiriate de clientul "+argument+" este "+controller.getNrCarti(argument)+"\n");
    }

    private void showInchirieri(){
        for(Book b:controller.getFaraInchiriere(Integer.parseInt(argument)))
            System.out.println(b);
    }

    public void run() {
        int cmd;
        while (true) {
            try {
                printMeniu();
                cmd = readInt("Introduceti comanda: ");
                if (cmd == 0)
                    return;
                execute(cmd);
            } catch (InputMismatchException ime) {
                System.out.println("\nTip de data invalid!\n");
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
    }
}
