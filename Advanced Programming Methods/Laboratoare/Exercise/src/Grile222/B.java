package Grile222;

interface A{
     default void doSmth(){
        this.doSmthElse();
    }
    void doSmthElse();
}

class B{
    public void doSmth() {
        System.out.println("B : doSmth");
    }
    public void doSmthElse() {
        System.out.println("B : doSmthElse");
    }
}

class C extends B implements A{
    public static void main(String[] args) {
        new C().doSmth();
    }
}
