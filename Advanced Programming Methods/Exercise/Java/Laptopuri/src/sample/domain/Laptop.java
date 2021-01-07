package sample.domain;

import sample.repository.HasID;
import sample.repository.Tip;

public class Laptop implements HasID<String> {
    private String laptopId;
    private String producator;
    private String model;
    private Tip tip;
    private float pret;

    public Laptop(String laptopId, String producator, String model, Tip tip, float pret) {
        this.laptopId = laptopId;
        this.producator = producator;
        this.model = model;
        this.tip = tip;
        this.pret = pret;
    }

    @Override
    public String getID() {
        return laptopId;
    }

    @Override
    public void setID(String laptopId) {
        this.laptopId = laptopId;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "laptopId='" + laptopId + '\'' +
                ", producator='" + producator + '\'' +
                ", model='" + model + '\'' +
                ", tip=" + tip +
                ", pret=" + pret +
                '}';
    }
}
