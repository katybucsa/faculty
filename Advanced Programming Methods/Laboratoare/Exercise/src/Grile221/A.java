package Grile221;

interface Interfata {
    void afiseaza();
}

class A {
    private class B implements Interfata{
        private int intreg;
        public B(int intreg){
            this.intreg=intreg;
        }

        @Override
        public void afiseaza() {
            System.out.println(intreg);
        }
    }

    public B getB(int a){
        return this.new B(10);
    }
}


class Main1{
    public static void main(String[] args) {
        A a=new A();
        Interfata b=a.getB(10);
    }
}


class AA{
    public AA(){}
    public String toString() { return "AA "; }
}
class BB extends AA{
    public BB(){}
    public String toString() { return "BB "+ super.toString(); }
}
class Main2 {
    public static void main(String[] args) {
        AA var1 = new BB();
        AA var2 = new AA();
        AA var3 = var1;
        BB var4 = (BB)var3;
        BB var5 = (BB)var2;
        System.out.print(var3);
        System.out.print(var4);
        System.out.print(var5);
    }
}


