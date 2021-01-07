public class Student extends Persoana{
    private int anStudiu;

    public Student(String nume,byte varsta,int anStudiu){
        super(nume,varsta);
        this.anStudiu=anStudiu;
    }

    public String toString(){
        return super.toString()+" "+anStudiu;
    }
}
