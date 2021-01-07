package Grile222;

class Main4 {

    public static void main(String[] args) {
        Ying ying = Ying.getInstance();
    }
}
class Ying{

    private Ying(){
        System.out.print("A");
    }

    static {
        System.out.print("B");
    }

    private static class Yang{

        static {
            System.out.print("C");
        }
        private static final Ying ying = new Ying();
        static {
            System.out.print("D");
        }
    }

    public static Ying getInstance(){
        System.out.print("E");
        return Yang.ying;
    }

    static {
        System.out.print("F");
    }

}