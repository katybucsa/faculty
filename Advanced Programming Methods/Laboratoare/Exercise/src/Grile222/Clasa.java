package Grile222;

public class Clasa {
    private String nume;
    public Clasa(String nume){
        this.nume=nume;
    }
    public void setNume(String num){
        this.nume=num;
    }
    public String getNume(){
        return nume;
    }
}

class Main {
    public static void main(String[] args) {
        Clasa c=new Clasa("Ana");
        Clasa c1=c;
        c1.setNume("Bianca");
        Clasa c2=c1;
        c2.setNume("Cornel");
        System.out.println(c.getNume());
        System.out.println(c1.getNume());
        System.out.println(c2.getNume());
    }
}
