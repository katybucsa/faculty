package sample.domain;

import javafx.util.Pair;
import sample.repository.HasID;

public class Spectacol implements HasID<Pair<PiesaTeatru,Locatie>> {
    private PiesaTeatru piesa;
    private Locatie locatie;
    private Integer ora;
    private Integer bileteVandute;


    public Spectacol(PiesaTeatru piesa, Locatie locatie, Integer ora,Integer bileteVandute) {
        this.piesa = piesa;
        this.locatie = locatie;
        this.ora = ora;
        this.bileteVandute=bileteVandute;
    }

    public Pair<PiesaTeatru,Locatie> getID(){
        return new Pair<>(piesa,locatie);
    }

    public void setID(Pair<PiesaTeatru,Locatie> pair){
        this.piesa=pair.getKey();
        this.locatie=pair.getValue();
    }

    public PiesaTeatru getPiesa() {
        return piesa;
    }

    public void setPiesa(PiesaTeatru piesa) {
        this.piesa = piesa;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public Integer getOra() {
        return ora;
    }

    public void setOra(Integer ora) {
        this.ora = ora;
    }

    public Integer getBileteVandute() {
        return bileteVandute;
    }

    public void setBileteVandute(Integer bileteVandute) {
        this.bileteVandute = bileteVandute;
    }

    @Override
    public String toString() {
        return "Spectacol{" +
                "piesa=" + piesa +
                ", locatie=" + locatie +
                ", ora=" + ora +
                '}';
    }
}
