package sample.domain;

import javafx.util.Pair;

import java.time.LocalDateTime;

public class Nota2 {
    private Pair<Integer,Integer> id;
    private String numeS;
    private double nota;
    private LocalDateTime data;
    private String feedback;


    public Nota2(int id1, int id2, String nume,double n,LocalDateTime dt,String feedback){
        id=new Pair<>(id1,id2);
        nota=n;
        data=dt;
        numeS=nume;
        this.feedback=feedback;
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
    public String getNumeS(){
        return numeS;
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
