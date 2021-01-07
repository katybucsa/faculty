package Curs5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FormulaHelper {
    static final int x=1;
    public static double partatBinom(double x, double y) {
        return Math.pow(x + y, 2);
    }
    //Formula f=(a,b)->{x=1;return 1;};
}


class Test{
    public static void main(String[] args) {
        Formula patratBinom=new Formula() {
            @Override
            public double calculate(double a, double b) {
                return patratBinom(a,b);
            }
        };
        double a=2,b=3;
        //double res=patratBinom.calculate(a,b);
        Formula f1=(x,y)->{return FormulaHelper.partatBinom(a,b);};
        System.out.println(f1.calculate(a,b));
        //a=3;b=4;
        //System.out.println(f1.calculate(a,b));
    }
}

class Reduce{
    public static void main(String[] args) {
        List<String> list= Arrays.asList("ddd2","aaa2","bbb1","aaa1",
                "ccc1","bbb2","aaa3","aaa4");
        Optional<String> op=list.stream()
                            .filter(x->x.startsWith("a"))
                            .reduce((x,y)->x.concat(y));
        op.ifPresent(System.out::println);
        String[] myArray={"this","is","a","sentence"};
        String r=Arrays.stream(myArray).reduce("",(a,b)->a+b);
        System.out.println(r);

        Integer[] l={1,9,5,2};
        Optional<Integer> res;
        res=Arrays.stream(l).reduce((x,y)->x>y ? x:y);
        res.ifPresent(System.out::println);
    }
}
