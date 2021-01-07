package Scris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

class K {
    public int x = 0;
}

class L {
    public static void main(String[] args) {
        L l = new L();
        System.out.println(l.foo().x);
    }

    public K foo() {
        K a = new K();
        try {
            a.x = 1;
            throw new NullPointerException();
        } catch (RuntimeException e) {
            a.x = 2;
            return a;
        } finally {
            a.x = 3;
        }
    }
}


class Main9 {
    public static void main(String[] args) {
        List<String> l = Arrays.asList("asd", "opd", "cas");
        var res = l.stream()
                .filter(x -> {
                    System.out.print(x);
                    return x.contains("a");
                })
                .map((x) -> {
                    System.out.println(x);
                    return x.toUpperCase();
                })
                .reduce("", (x, y) -> x + y);
        System.out.println(res);
    }
}


class A4<E, T> {
    private E e;
    private T t;

    public void valueE(E e) {
        this.e = e;
    }

    public void valueT(T t) {
        this.t = t;
    }

    public E value() {
        return e;
    }

    public T value1() {
        return t;
    }

}

class M {
    protected int numar;

    protected M(int nr) {
        numar = 2 * nr;
    }
}

class Bm extends M{
    public Bm(int nr) {
        super(nr);
    }

    public static void main(String[] args) {
        M m=new Bm(10005001);
        System.out.println(m.numar);
        TreeSet<Bm> t=new TreeSet<>();
    }
}


class Main10 {
    public static void main(String[] args) {
        A4 a = new A4<String, Integer>();
        a.valueE(1010);
        a.valueE("asd");
        //System.out.println(a.value()+" "+a.value1());


        var res = Arrays.asList("asd ", "bus ", "aop ").stream()
                .filter(s -> {
                    System.out.println(s);
                    return s.contains("a");
                })
                .map(x -> {
                    System.out.println(x);
                    return x.toUpperCase();
                })
                .reduce("", (x, y) -> {
                    System.out.println(10);
                    return x + y;
                });
        System.out.println(res);
    }
}

/*class Patrat{
    public int latimea;
    public Patrat(int i){
        latimea=i;
    }
}

class Dreptunghi extends Patrat{
    public int lungimea;
    public Dreptunghi(int l,int L){
        super(l);
        lungimea=L;
    }

    public void zoomIn(int dx){
        super(latimea+dx);
    }
}*/
