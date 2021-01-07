package Curs1_2;

public class Exemplu{
    int x;
    static long n;
    public void metodaDeInstanta(){
        n++; //corect
        x--; //corect
    }

    public static void metodaStatica(){
        n++; //corect
        //x--; //eroare la compilare! o variabila de instanta nu poate fi modificata dintr-o metoda statica
    }
}
