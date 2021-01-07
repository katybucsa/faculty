package domain;

import factory.HasID;

/**
 * Tema class
 * implements HasID interface - type Tema must have an attribute of type Integer
 */
public class Tema implements HasID<Integer> {
    private int nrTema;
    private String descriere;
    private int deadline;
    private int saptPrimireTema;

    /**
     * the constructor of an object of Tema type
     * @param nr - the id(homework number) of the Tema object that will be created
     * @param desc - the description of the homework
     * @param dl - the deadline of the homework
     * @param spt - the week in which the homework was received
     */
    public Tema(int nr, String desc, int dl,int spt){
        this.nrTema=nr;
        this.descriere=desc;
        this.deadline=dl;
        this.saptPrimireTema=spt;
    }

    /**
     * getter method
     * @return the id of the current Tema object
     */
    public Integer getID(){
        return this.nrTema;
    }

    /**
     * getter method
     * @return the desctiprion of the current Tema object
     */
    public String getDescriere(){
        return this.descriere;
    }

    /**
     * getter method
     * @return the deadline of the current Tema object
     */
    public int getDeadline(){
        return this.deadline;
    }

    /**
     * getter method
     * @return the week in which the homework was received of the current Tema object
     */
    public int getSaptPrimireTema(){
        return this.saptPrimireTema;
    }

    /**
     * setter method
     * changes the id of the current Tema object
     * @param id - the new id
     */
    public void setID(Integer id){
        this.nrTema=id;
    }

    /**
     * setter method
     * cahnges the description of the current Tema object
     * @param desc - the new description
     */
    public void setDescriere(String desc){
        this.descriere=desc;
    }

    /**
     * setter method
     * changes the deadline of the current Tema object
     * @param dl - the new deadline
     */
    public void setDeadline(int dl){
        this.deadline=dl;
    }

    /**
     * setter method
     * changes the week in which the homework was received
     * @param spt - the new week in which the homework was received
     */
    public void setSaptPrimireTema(int spt){
        this.saptPrimireTema=spt;
    }

    /**
     * override method
     * @return a string containing the display format of an instance of Tema class
     */
    public String toString(){
        return this.nrTema+" "+this.descriere+" "+this.deadline+" "+this.saptPrimireTema;
    }
}
