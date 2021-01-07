package Curs1_2;

public class Student extends Persoana {
    private int anStudiu;

    public Student(String nume, int varsta, int anStudiu) {
        super(nume,varsta);
        this.anStudiu = anStudiu;
    }

    public Student(){}

    public int getAnStudiu() {
        return anStudiu;
    }

    public void setAnStudiu(int anStudiu) {
        this.anStudiu = anStudiu;
    }

    @Override
    public String toString() {
        return "Student{" +super.toString()+" "+
                "anStudiu=" + anStudiu +
                '}';
    }
}
