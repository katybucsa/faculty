package Curs1_2;

public class Main {
    public static void initStudent(Student s, String nume, int varsta, int anStudiu){
        s.setNume(nume);
        s.setVarsta(varsta);
        s.setAnStudiu(anStudiu);
    }


    public static void main(String[] args) {/*
        byte a;
        int valoare=100;
        final double PI=3.14;
        long numarElemente=12345678L;
        System.out.println(numarElemente);

        final int MAX=100;
        final int MAX2;
        MAX2=100;

        int[][] x;
        x=new int[5][5];
        x[0][0]=2;
        int y=x[2][2];//y=0


        Student s= new Student();
        initStudent(s,"a",19,2);
        System.out.println(s);//se modifica s

        Integer intObj1=new Integer(34);
        Integer intObj2=new Integer("34");
        Boolean boolV1=new Boolean("true");
        Integer intObj3=Integer.valueOf("36");
        Integer intObj4=Integer.valueOf("1000",2);//8 in baza 2
        System.out.println(intObj4);
        Integer intO1=23;//autoboxing
        int intPrimitiv=intO1;//unboxing(dezveleste de clasa invelitoare Integer)
        intPrimitiv++;
        Integer intO2=intPrimitiv;//autoboxing*/


        Persoana stud=new Student("Andu",(byte)18,3);
        System.out.println(stud.toString());
    }
}