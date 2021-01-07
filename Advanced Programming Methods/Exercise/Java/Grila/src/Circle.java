public class Circle extends Shape {
    static void print() {
        System.out.println("Circle print");
    }

    protected void print2() {
        System.out.println("Circle " + (super.x + super.y) + 1);
    }
}
