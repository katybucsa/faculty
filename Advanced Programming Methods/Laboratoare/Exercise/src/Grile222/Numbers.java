package Grile222;

class Numbers {

    int number = 5;

    Numbers(int number){
        number=number+10;
        initNumber(number);
        this.number = number;
    }

    void initNumber(int number){
        number = number+10;
    }
    void printNumber(int number) {
        System.out.println("Number = " + this.number);
    }

    public static void main (String args[]) {
        Numbers number = new Numbers(15);
        number.printNumber(20);
    }
}


class Student {
    private String nume;
    public Student(String nume) {
        this.nume = nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getNume() {
        return nume;
    }
}


class Mainn {
    public static void f(Student s,String str) {
        s = new Student("ion2");
        str="dsa";
    }
    public static void g(Student s,String str) {
        s.setNume("ion2");
        str.concat("fd");
    }
    public static void main(String[] args) {
        Student s = new Student("ion");
        String str="aca";
        f(s,str);
        System.out.println(s.getNume());
        System.out.println(str);
        g(s, str);
        System.out.println(s.getNume());
        System.out.println(str);
    }

}
