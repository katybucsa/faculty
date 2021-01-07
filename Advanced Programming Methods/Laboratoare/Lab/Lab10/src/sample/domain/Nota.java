package sample.domain;

import javafx.util.Pair;
import sample.repository.HasID;

import java.time.LocalDateTime;

public class Nota implements HasID<Pair<Integer,Integer>> {
    private Pair<Integer,Integer> id;
    private double nota;
    private LocalDateTime data;
    private String feedback;


    public Nota(int id1, int id2, double n, LocalDateTime d, String fb){
        id=new Pair<>(id1,id2);
        nota=n;
        data=d;
        feedback=fb;
    }

    public Pair<Integer,Integer> getID(){
        return id;
    }

    public Integer getIdS(){
        return id.getKey();
    }

    public Integer getNrT(){
        return id.getValue();
    }

    public double getNota(){
        return nota;
    }

    public LocalDateTime getData(){
        return data;
    }

    public String getFeedback(){
        return  feedback;
    }

    public void setID(Pair<Integer,Integer> p){
        id=p;
    }

    public void setNota(double n){
        nota=n;
    }

    public void setData(LocalDateTime d)
    {
        data=d;
    }

    public void setFeedback(String fb){
        feedback=fb;
    }

    public String toString(){
        return id.getKey()+"\t"+id.getValue()+"\t"+nota+"\t"+data;
    }
}
