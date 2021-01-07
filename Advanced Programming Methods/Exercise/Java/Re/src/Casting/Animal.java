package Casting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Animal {
    /*int x=1;
    public Animal(int x){
        this.x=x;
    }*/
    public void eat() {
        System.out.println("Animal");
    }

    /*public void setX(int c){
        x=c;
    }*/

    public void x(){}
}

class Cat extends Animal {

    public void eat() {
        System.out.println("Cat");
    }

    public void meow() {
        // ...
    }
}

class Dog extends Animal {

    public void eat() {
        System.out.println("Dog");
    }
}

class AnimalFeeder {

    public void feed(List<Animal> animals) {
        animals.forEach(animal -> {
            animal.eat();
        });
    }
}

class ABC{
    private static int m;
    public static void modif(Animal a,String str){
        str="Se modifica";
       // a.x=10;
        m=0;
    }
}

class Main{

    public static void main(String[] args) {
       /* Animal a=new Animal(12);
        String str="Nu se modifica";
        ABC.modif(a,str);
        System.out.println(a.x);
        System.out.println(str);*/
        List<Animal> animals = new ArrayList<>();
        Animal a=new Animal();
        Cat c=new Cat();
        animals.add(a);
        animals.add(c);
       // animals.forEach(x->x.eat());
        new AnimalFeeder().feed(animals);
        Pisica p1=new Pisica();
        p1.showIdentity();
        Felina p2=new Felina();
        p2.showIdentity();
        Pantera p3=new Pantera();
        p3.showIdentity();
        List<Felina> colectie=new ArrayList<Felina>();
        colectie.add(p1);
        colectie.add(p2);
        //colectie.add(p3);
        //colectie.forEach(x->x.showIdentity());
        System.out.println(p3.printInfo(p1)+" "+p3.printInfo(p2));

        List<?> listOfAnyType;
        List<Object> listOfObject = new ArrayList<Object>();

        List<String> listOfString = new ArrayList<String>();
        for(int i=1; i<4; i++)
            listOfString.add(String.valueOf(i));


        //listOfObject = (List<Object>) listOfString;
        for(Object e : listOfObject)
        {
            System.out.println(e);
        }

        listOfAnyType = listOfString;
        for(Object e : listOfAnyType)
        {
            System.out.println(e);
        }


    }
}

class Felina{
    public void showIdentity(){
        System.out.println("Felina");
    }
}

class Pisica extends Felina{
    public void showIdentity(){
        System.out.println("Pisica");
    }
}

class Pantera extends Felina{
    public void showIdentity(){
        System.out.println("Pantera");
    }
    public String printInfo(Felina f){
        return "Felina";
    }

    public String printInfo(Pisica p){
        return "Pisica";
    }
}



class Hello<T> {
    T t;
    public Hello(T t) { this.t = t; }
    public String toString() { return t.toString(); }

    public static void main(String[] args) {
        System.out.print(new Hello<>("hi"));
        System.out.print(new Hello("there"));
    }
}


abstract class A1 {
    A1() {
        System.out.println("A1()");
    }

    abstract void print();
}

class B1 extends A1{
    B1(){
        System.out.println("B1()");
    }
    @Override
    void print(){
        B1 b = new B1();
        b.print();
        System.out.println("printB");
    }
}


class M{
    public static void main(String[] args) {
        B1 b = new B1(){
            void print(){
                System.out.println("print");
            }
        };
        b.print();

    }
}


class Test {
    static void print(Object object) {
        System.out.println("object");
    }

    static void print(String string) {
        System.out.println("string");
    }
}


class Proposal {
    public static void main(String[] args) {
        Pisica p1=new Pisica();
        Felina p2=new Felina();
        Pantera p3=new Pantera();
        List basket=new ArrayList();
        basket.add(p1);
        basket.add(p2);
        basket.add(p3);
        List<? extends Felina> fridge = new ArrayList<>(basket);
        fridge.forEach(fruit->System.out.print(fruit + " "));
        List<? extends Felina> store = new ArrayList<>(10);
        Collections.copy(store, basket);


        execute(null);
    }

    static void execute(Test test) {
        test.print(null);
    }
}
