package Curs3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class Outer {
    private static int i=1;
    public int getValue(){
        return i;
    }

    public Outer(){
        System.out.println("DA");
    }
    public static class Inner{
        private int k;
        public Inner(int i){
            //this.k=Outer.this.i+i;
        }
        public int value(){
            return k;
        }
    }
    public Inner getInnerInstance(int x){
        return new Inner(x);
    }

}

class Main {
    public static void main(String[] args) {
        //Outer outer=new Outer();
        Outer.Inner out=new Outer.Inner(2);
        //Outer.Inner inner=outer.new Inner(2);
        //Outer.Inner inner1=outer.getInnerInstance(2);
        Outer3 outer3=new Outer3();
        Hidden hidden=outer3.getInnerInst();
        System.out.println(hidden.value());
        //Stack<int> si=new Stack<>(); //eroare la compilare
    }
}

interface Hidden{
    int value();

}
class Outer5{
    private int g=0;
    public Hidden getInnerInstance(int i){
        return new Hidden() {
            private int k=10;
            @Override
            public int value() {
                return k;
            }
        };
    }

}
class Outer4{
    public Hidden getInnerInstance(int i){
        if(i==11){
            class BlockInner implements Hidden{
                private int k=10;
                public int value(){
                    return k;
                }
            }
            return new BlockInner();
        }
        return null;
    }

}
class Outer3{
    public Hidden getInnerInst(){
        int[] a={9};
        class MethodHidden implements Hidden{
            private int i=11;
            public int value(){
                i+=a[0];
                a[0]++;
                return i;
            }
        }
        return  new MethodHidden();
    }

}

class Stack1<E>{
    E[] items;
    int vf=0;
    public Stack1(Class tip){
        items= (E[])Array.newInstance(tip,20);
    }

}
class Singleton<T>{
    /*private static T instance=null;
    public static T getOnstanse(){
        if(instance==null)
            instance=new Singleton<T>();
        return instance;
    }*/
    public void dsf(List<?> l){
        System.out.println(l.get(0));
    }

}
