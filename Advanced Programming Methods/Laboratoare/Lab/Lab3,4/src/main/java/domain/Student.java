package domain;

import factory.HasID;

/**
 * Student class
 * implements HasID interface - type Student must have an attribute of type Integer
 */
public class Student implements HasID<Integer> {
    private int idStudent;
    private String nume;
    private int grupa;
    private String email;
    private String indrumator;

    /**
     * the constructor for an object of Student type
     * @param id - the id of the new Student that will be created
     * @param n - the name of the new Student that will be created
     * @param g - the group number of the new Student
     * @param e - the email of the new Student
     * @param i - the name of the lab teacher
     */
    public Student(int id,String n,int g,String e,String i){
        this.idStudent=id;
        this.nume=n;
        this.grupa=g;
        this.email=e;
        this.indrumator=i;
    }

    /**
     * getter method
     * @return the id of the current student
     */
    public Integer getID(){
        return idStudent;
    }

    /**
     * setter method
     * changes the id of the current student
     * @param id - Integer, the new id
     */
    public void setID(Integer id){
        this.idStudent=id;
    }

    /**
     * getter method
     * @return the name of the current student
     */
    public String getNume(){
        return this.nume;
    }

    /**
     * getter method
     * @return the group number of the current student
     */
    public int getGrupa(){
        return this.grupa;
    }

    /**
     * getter method
     * @return the email of the current student
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * getter method
     * @return the name of the lab teacher of the current student
     */
    public String getIndrumator(){
        return this.indrumator;
    }

    /**
     * setter method
     * changes the name of the current student
     * @param new_n - String, the new name
     */
    public void setNume(String new_n){
        this.nume=new_n;
    }

    /**
     * setter method
     * changes the group number of the current student
     * @param new_g -  Integer, the new group number
     */
    public void setGrupa(int new_g){
        this.grupa=new_g;
    }

    /**
     * setter method
     * changes the email of the current student
     * @param new_e - String, the new email
     */
    public void setEmail(String new_e){
        this.email=new_e;
    }

    /**
     * setter method
     * changes the name of the lab teacher of the current student
     * @param new_i - the new name of the lab teacher
     */
    public void setIndrumator(String new_i){
        this.indrumator=new_i;
    }

    /**
     * override method
     * @return a string containing the display format of an instance of Student class
     */
    @Override
    public String toString(){
        return this.idStudent+"\t\t"+this.nume+"\t\t"+this.grupa+"\t\t"+this.email+"\t\t\t"+this.indrumator;
    }
}
