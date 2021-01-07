package bugs.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements Serializable, HasID<String> {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @OneToMany(mappedBy = "responsibleProgrammer")
    private Set<Bug> bugsToResolve = new HashSet<>();

    @OneToMany(mappedBy = "solvedBy")
    private Set<Bug> solvedBugs = new HashSet<>();


    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Role role, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Bug> getBugsToResolve() {
        return bugsToResolve;
    }

    public void setBugsToResolve(Set<Bug> bugsToResolve) {
        this.bugsToResolve = bugsToResolve;
    }

    public Set<Bug> getSolvedBugs() {
        return solvedBugs;
    }

    public void setSolvedBugs(Set<Bug> solvedBugs) {
        this.solvedBugs = solvedBugs;
    }
}