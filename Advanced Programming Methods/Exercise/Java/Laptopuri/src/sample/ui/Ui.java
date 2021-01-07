package sample.ui;

import sample.controller.Controller;
import sample.domain.Client;
import sample.domain.Laptop;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Ui {
    private Controller controller;
    private String argument;

    public Ui(Controller controller,String argument) {
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
            showByProducator();
        /*else if(cmd==2)
            showClientNrReservations();
        else if(cmd==3)
            showSumaIncasataHotel();*/
        else
            System.out.println("Comanda invalida\n");
    }


    private void showByProducator(){
        for(Laptop  l:controller.getByProducator()){
            System.out.println(l);
        }
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
