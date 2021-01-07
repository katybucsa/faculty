public class Persoana {
    protected String nume;
    protected byte varsta;
    public Persoana(){
        this("",(byte)0);
    }

    public Persoana(String nume,byte varsta){
        this.nume=nume;
        this.varsta=varsta;
    }

    public String toString(){
        return nume+"  "+varsta;
    }

}
