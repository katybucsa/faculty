package Scris;

public class AAA {
    static int x,y;
    {
        x=y=1010;
    }
    void m1(){
        System.out.print("m1 ");
    }
    static void m2(){
        System.out.print(x+y+"10 ");
    }
}

class BBB extends AAA{
    void m1(){
        System.out.print(x+y+"10 ");
    }
    static void m2(){
        System.out.print("BBB "+x+y+"10 ");
    }
}

class G4{
    public static void main(String[] args) {
        AAA aaa=new BBB();
        aaa.m1();
        aaa.m2();
    }
}