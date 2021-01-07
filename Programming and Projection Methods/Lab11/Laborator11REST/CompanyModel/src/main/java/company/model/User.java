package company.model;


import java.io.Serializable;

public class User implements Serializable, HasID<String> {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getID() {
        return username;
    }

    @Override
    public void setID(String s) {
        this.username = s;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        this.password = p;
    }
}