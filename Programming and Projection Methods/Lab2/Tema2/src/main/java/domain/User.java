package domain;

import repository.HasID;

public class User implements HasID<String> {
    private String username;
    private String password;
    private String name;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    @Override
    public String getID() {
        return username;
    }

    @Override
    public void setID(String s) {
        this.username=s;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String p){
        this.password=p;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        this.name=n;
    }
}