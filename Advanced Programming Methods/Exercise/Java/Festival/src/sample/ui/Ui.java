package sample.ui;

import sample.controller.Controller;
import sample.domain.Locatie;
import sample.domain.PiesaTeatru;
import sample.domain.Spectacol;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    private Controller controller;

    public Ui(Controller controller) {
        this.controller = controller;
    }

    private void printMeniu() {
        System.out.println("1.  Afisati toate piesele de teatru si pentru fiecare piesa locatiile\n" +
                        "2. Afisati numarul de spectacole pentru fiecare piesa\n" +
                        "3. Afisati un program al festivalului\n" +
                        "4.Afisati fiecare piesa cu numarul de bilete vandute.\n");
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
            showPieseLocatii();
        else if(cmd==2)
            showNrSpectacole();
        else if(cmd==3)
            showProgramFestival();
        else if(cmd==4)
            showNrBileteVandute();
        else
            System.out.println("Comanda invalida\n");
    }

    private void showPieseLocatii(){
        Map<PiesaTeatru, List<Spectacol>> map=controller.getPieseLocatiiMap();
        for(Map.Entry<PiesaTeatru,List<Spectacol>> entry:map.entrySet()){
            System.out.println("Piesa de teatru: "+entry.getKey());
            for(Spectacol s:entry.getValue()){
                System.out.println("\t\t"+s.getLocatie());
            }
        }
    }

    private void showNrSpectacole(){
        Map<PiesaTeatru, Long> map=controller.getNrSpectacole();
        for(Map.Entry<PiesaTeatru,Long> entry:map.entrySet()){
            System.out.println("Piesa de teatru: "+entry.getKey()+", Numar spectacole: "+entry.getValue());
        }
    }

    private void showProgramFestival(){
        for(Map.Entry<Locatie,List<Spectacol>> entry:controller.getProgramFestival().entrySet()){
            System.out.println(entry.getKey());
            for(Spectacol s:entry.getValue()){
                System.out.println("\t\t"+s);
            }
        }
    }


    private void showNrBileteVandute(){
        for(Map.Entry<PiesaTeatru,Integer> entry:controller.getPieseNrBileteVandute().entrySet()){
            System.out.println("Piesa de teatru: "+entry.getKey()+",  Bilete vandute: "+entry.getValue()+"\n");
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
