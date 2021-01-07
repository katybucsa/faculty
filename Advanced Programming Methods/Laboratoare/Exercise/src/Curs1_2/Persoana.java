package Curs1_2;

public class Persoana {
    private String nume;
    private int varsta;

    public Persoana(){}
    public Persoana(String nume,int varsta){
        this.nume=nume;
        this.varsta=varsta;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", varsta=" + varsta +
                '}';
    }
}
