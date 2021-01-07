package domain;

import factory.HasID;
import javafx.util.Pair;
import java.time.LocalDateTime;

public class Nota implements HasID<Pair<Integer,Integer>> {
    private Pair<Integer,Integer> id;
    private double nota;
    private LocalDateTime data;


    public Nota(int id1, int id2, double n, LocalDateTime d){
        id=new Pair<>(id1,id2);
        nota=n;
        data=d;
    }

    public Pair<Integer,Integer> getID(){
        return id;
    }

    public double getNota(){
        return nota;
    }

    public LocalDateTime getData(){
        return data;
    }

    public void setID(Pair<Integer,Integer> p){
        id=p;
    }

    public void setNota(double n){
        nota=n;
    }

    public void setData(LocalDateTime d){
        data=d;
    }

    public String toString(){
        return id.getKey()+"\t"+id.getValue()+"\t"+nota+"\t"+data;
    }
}
