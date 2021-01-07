package GrileProfa;

import java.util.Arrays;

interface JavaInterface {
    /*protected int x = 10;
    int y;
    int z = 20;

    default int x() {
        return 0;
    }

    abstract void foo();

    final int f(int x);*/
}

class A {
    int i;

    public A() {
        System.out.print("A()");
    }
}

class B extends A {
    float i = 3;

    public B() {
        System.out.print("B()");
    }
}

class AB extends B {
    public static void main(String argv[]) {
        Arrays.asList(new AB(), new AB(), new AB()).forEach(x -> x.testMethodCA("AB"));
    }

    public void testMethodCA(String s) {
        System.out.println(s);
    }
}


class AAA {
    static class CCC {
    }

    class BBB {
    }
}

class Ex4_224 {
    public static void main(String[] args) { // in main
        AAA a = new AAA();
        AAA.BBB b = a.new BBB();
        AAA.CCC c = new AAA.CCC();
    }
}