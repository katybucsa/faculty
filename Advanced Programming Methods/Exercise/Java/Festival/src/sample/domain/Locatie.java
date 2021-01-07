package sample.domain;

import sample.repository.HasID;

public class Locatie implements HasID<String> {
    private String nume;
    private Integer locuri;

    public Locatie(String nume, Integer locuri) {
        this.nume = nume;
        this.locuri = locuri;
    }

    public String getID() {
        return nume;
    }

    public void setID(String nume) {
        this.nume = nume;
    }

    public Integer getLocuri() {
        return locuri;
    }

    public void setLocuri(Integer locuri) {
        this.locuri = locuri;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "nume='" + nume + '\'' +
                ", locuri=" + locuri +
                '}';
    }
}
