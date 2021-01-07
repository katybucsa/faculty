package sample.ui;

import sample.controller.Controller;
import sample.domain.Client;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

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
            showLocations();
        else if(cmd==2)
            showClientNrReservations();
        else if(cmd==3)
            showSumaIncasataHotel();
        else
            System.out.println("Comanda invalida\n");
    }

    private void showLocations(){
        controller.getLocations(argument).forEach(x->System.out.println(x));
        System.out.println("\n");
    }

    private void showClientNrReservations(){
        for(Map.Entry<Client,Long> entry:controller.getClientNrReservations().entrySet()){
            System.out.println("ClientName: "+entry.getKey().getName()+"  NrRezervari: "+entry.getValue());
        }
        System.out.println("\n");
    }

    private void showSumaIncasataHotel(){
        for(Map.Entry<String,Double> entry:controller.getSumaIncasataHotel().entrySet()){
            System.out.println("Hotel: "+entry.getKey()+", Suma incasata: "+entry.getValue());
        }
        System.out.println("\n");
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
