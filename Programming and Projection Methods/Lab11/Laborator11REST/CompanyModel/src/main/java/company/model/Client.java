package company.model;

import java.io.Serializable;

public class Client implements Serializable, HasID<Integer> {
    private int client_id;
    private String name;

    public Client(int id, String name){
        this.client_id=id;
        this.name=name;
    }

    @Override
    public Integer getID() {
        return client_id;
    }

    @Override
    public void setID(Integer integer) {
        this.client_id=integer;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }
}
