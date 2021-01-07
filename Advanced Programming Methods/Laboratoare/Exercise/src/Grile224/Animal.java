package Grile224;

import java.util.ArrayList;

class Pizza{
    private int id;
    Pizza(int id){this.id=id;}
    boolean equals(Pizza obj){return this.id==obj.id;}
}

class PizzaWithCheese extends Pizza{
    private String topping;
    PizzaWithCheese(int id,String topping){super(id);this.topping=topping;}
    boolean equals(PizzaWithCheese piz){return super.equals(piz) && this.topping.equals(piz.topping);}
}


class Main8{
    public static void main(String[] args) {
        PizzaWithCheese pizza1=new PizzaWithCheese(1,"mozzarella");
        PizzaWithCheese pizza2=new PizzaWithCheese(1,"gounda");
        Pizza pizza3=new PizzaWithCheese(1,"gorgonzola");
        Pizza[] x={pizza1,pizza2};
        System.out.print(pizza1.equals(pizza2)+" ");
        System.out.print(x[0].equals(x[1])+" ");
        System.out.print(pizza3.equals(pizza2)+" ");
    }
}

class Animal {
    private String identitate;

    public Animal() { this.setIdentitate("animal"); }

    public String getIdentitate() { return identitate; }

    public void setIdentitate(String identitate) { this.identitate = identitate; }
}

class Lup extends Animal {
    public Lup() {
        super();
        super.setIdentitate("lup");
    }
}

class Main7 {

    static void afiseazaAnimale(ArrayList<? super Lup> lista) {
        /*lista.forEach(x -> {
            System.out.println(x.getIdentitate());
        });*/
    }

    public static void main(String[] args) {
        ArrayList<Animal> lista = new ArrayList<>();
        Animal a1 = new Animal();
        Animal a2 = new Lup();
        Lup a3 = new Lup();
        System.out.println(a1.getIdentitate());
        System.out.println(a2.getIdentitate());
        System.out.println(a3.getIdentitate());
        lista.add(a1);
        lista.add(a2);
        lista.add(a3);
        afiseazaAnimale(lista);
        final int m;

    }
}
