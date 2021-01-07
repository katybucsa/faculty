package Exceptions;

import java.util.*;

public class Exceptie {
    public static int f(int i){
        try{
            System.out.print(i);
            return 1;
        }finally {
            try{
                return 10/0;
            }catch (Throwable e){
                return 111;
            }finally {
                System.out.print(1110);
                return 3;
            }
        }
    }

    public static int foo(){
        int x=0;
        try{
            x=1;
            throw new NullPointerException();
        }catch (Exception e){
            x=2;
            return x;
        }finally {
            x=3;
        }
    }

    public static void main(String[] args) {
        System.out.println(foo());
        //System.out.println(f(2));
    }
}

/*
static class MyOtherException extends MyException{
    MyOtherException(String mesaj){super(mesaj);}
}

static class MyException extends Exception{
    MyException(String mesaj){super(mesaj);}
}


class X{
    private static void f(int i) throws MyException {

        if (i > 5)
            throw new MyException("peste 5");
        else
            throw new MyOtherException("sub 5");

    }

    public static void main(String[] args) {

        try {

            f(6);

        } catch (MyException e ) {
            System.out.println(e);

        } catch (MyOtherException e){
            System.out.println(e);

        }

    }

}*/


class Excep extends Error {
    public Excep(String message){
        super(message);
    }
}
class A {
    public void  afis(String s){
        if(s.equals("")){ throw new Excep("E gol"); }
        else{ System.out.println(s); }
    }
}


class Aaa
{

    String str="A ";
    Aaa()
    {
        try{
            throw  new Exception();
        }
        catch (Exception exc)
        {
            str+="AE ";
        }
    }
    public void show(){
        str+="AS ";
    }
}
class B extends Aaa {
    B()
    {
        str+="B ";
    }
    @Override
    public void show()
    {

        try
        {
            str +="B1 ";
            show2();
        }
        catch (Exception e)
        {
            str += "BE1 ";
        }
    }
    public void show2() throws Exception
    {
        try
        {
            str += "B2" ;
            show3();
        }
        catch(Exception e)
        {
            throw new Exception();
        }
        finally
        {
            str += "BF ";
        }

        str += "BS2 ";
    }
    public void show3() throws Exception
    {
        throw new Exception();
    }
}
class App {

    public static void main(String[] args) throws  Exception{

        Aaa a=new B();
        a.show();
        System.out.print( a.str);
    }
}
class AAa<E,T>
{

    private E e;
    private T t;
    public void setValue(E e) { this.e = e; }

    public void setValue1(T t){ this.t = t; }

    public E getValue(){ return e; }

    public T getValue1(){ return t; }

}

class Main2 {
    public static void main(String[] args) {

        /*AAa a=new AAa();
        a.setValue(1);
        a.setValue("fds");
        System.out.println(a.getValue());
        A obj= new A();
        obj.afis("bafta");
        obj.afis("la" );
        obj.afis("");
        obj.afis("examen");*/

        ArrayList<Animal> lista = new ArrayList<>();
        Animal a1 = new Animal();
        Animal a2 = new Lup();
        Lup a3 = new Lup();
        lista.add(a1);
        lista.add(a2);
        lista.add(a3);
        afiseazaAnimale(lista);


        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("String1");
        treeSet.add("fff");
        treeSet.add("String1");
        treeSet.add("alibi");

        System.out.println(treeSet);

        HashSet<String> hashSet = new HashSet<>();

        hashSet.add("String1");
        hashSet.add("fff");
        hashSet.add("String1");
        hashSet.add("alibi");

        System.out.println(hashSet);

    }
    static void  afiseazaAnimale(ArrayList<? extends Animal> lista){
        lista.forEach(x -> {
            System.out.println(x.getIdentitate());
        });
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



