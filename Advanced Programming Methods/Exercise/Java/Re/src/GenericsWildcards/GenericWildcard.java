package GenericsWildcards;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GenericWildcard {

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        ints.add(3); ints.add(5); ints.add(10);
        double sum = sum(ints);
        System.out.println("Sum of ints="+sum);
    }

    public static double sum(List<? extends Number> list){
        double sum = 0;
        for(Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }
}


interface Foo{
    boolean foo(int x);
    default double mas(double x){
        return 1;
    }
}

class X implements Foo{
    public boolean foo(int x){
        return true;
    }

    /*public double mas(double x){
        return 2;
    }*/
}

class A{
    public boolean foo(int x){return x%2<1;}
    public boolean ma(int x){return true;}
}
class B extends A{
    public  boolean foo(int x){return x%2>=1;}
}

class Main1 {
    public static void main(String[] args) {
      /*  List<? super String> list=new ArrayList<String>();
        list.add(null);
        list.add("ads");*/
        X x=new X();
        System.out.println(x.mas(10));

        A a = new B();
        Foo foo = a::ma;

        /*ArrayList<Integer> list = new ArrayList<>();
        int[] numbers = new int[]{1, 2, 3, 4, 5, 6};

        for (int i = 0; i < numbers.length; i++)
            if (foo.foo(numbers[i]))
                list.add(numbers[i]);*/

        /*Arrays.asList(10,6,7,4,12,34,45,3,54,6)
                .stream()
                //.peek(x->System.out.println(x))
                //.sorted()
                //.peek(x->System.out.println(x))
                .distinct()
                .peek(x->System.out.println(x))
                .skip(5)
                .peek(x-> System.out.println(x))
                .forEach(x-> System.out.print(""));
                /*.allMatch(x->{
                    System.out.println(x);return x>=6;});
                /*.reduce(0,(a,b)->{
                    System.out.println(""+a+" "+b);
                    return a+b;
                });*/

    }
}

interface Formula {
    int x=0;
    double calculate(double a);
}


abstract class Aa implements Formula
{
    public static void locVariable() {
        int var =-2;
        Formula modulLambda = (double a) -> {
            return Math.abs(a*var);
        };
        double rez = modulLambda.calculate(5);
        System.out.printf("%.0f",rez);
    }

}
class BB implements Formula{

    @Override
    public double calculate(double a) {
        return 0;
    }
}
class  Main3 {
    public static void main(String[] args) {
        Formula f=new BB();
        //Aa.locVariable();
        /*List<String> list =
                Arrays.asList("d2","a1","c1", "b2","d1","a4");
        Optional<String> op=list
                .stream().reduce((x,y) -> x.concat(y))
                .filter(x->x.endsWith("2"));

        op.ifPresent(System.out::println);*/



    }
}


class LFormula {
    private int a=0;
    private static int b=0;
    public int var(int x, int y){
        int c=1;
       // Formulaa f = (o, p)->{ a=3; b=2; c=a*b*c; return Math.max(o,p+Math.max(o,c));};
       // return f.calculate(x,y);

        return 0;
    }
}
interface Formulaa{
    int x=0;
    int calculate(int a, int b);
}



