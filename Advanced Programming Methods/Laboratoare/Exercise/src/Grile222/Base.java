package Grile222;

public class Base {

    public static void display() {

        System.out.println("metoda privata statica din Base");

    }

    public void print() {

        System.out.println("metoda publica din Base");

    }

}

class Derived extends Base {
    public static void display() {

        System.out.println("metoda privata statica din Derived");

    }

    public void print() {

        System.out.println("metoda publica din Derived");

    }

}

class Main5 {

    public static void main(String[] args) {
        Base obj = new Derived();
        obj.display();
        obj.print();

    }

}
