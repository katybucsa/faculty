package sample.domain;

import sample.repository.HasID;

import java.util.Objects;

public class PiesaTeatru implements HasID<String> {
    private String id;
    private String titlu;
    private String autor;


    public PiesaTeatru(String id,String titlu,String autor){
        this.id=id;
        this.titlu=titlu;
        this.autor=autor;
    }


    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "PiesaTeatru{" +
                "id='" + id + '\'' +
                ", titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }

}
