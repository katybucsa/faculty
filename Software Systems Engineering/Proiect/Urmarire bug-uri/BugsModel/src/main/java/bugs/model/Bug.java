package bugs.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Bug")
public class Bug implements Serializable, HasID<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "naming")
    private String naming;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "stage")
    private Stage stage;

    @ManyToOne
    @JoinColumn(name = "responsibleProgrammer")
    private User responsibleProgrammer;

    @ManyToOne
    @JoinColumn(name = "solvedBy")
    private User solvedBy;

    public Bug() {

    }

    public Bug(String naming, String description, Stage stage) {
        this.naming = naming;
        this.description = description;
        this.stage = stage;
    }

    public Bug(Integer id, String naming, String description, Stage stage) {
        this.id = id;
        this.naming = naming;
        this.description = description;
        this.stage = stage;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer integer) {
        this.id = integer;
    }

    public String getNaming() {
        return naming;
    }

    public void setNaming(String naming) {
        this.naming = naming;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getResponsibleProgrammer() {
        return responsibleProgrammer;
    }

    public void setResponsibleProgrammer(User responsibleProgrammer) {
        this.responsibleProgrammer = responsibleProgrammer;
    }

    public User getSolvedBy() {
        return solvedBy;
    }

    public void setSolvedBy(User solvedBy) {
        this.solvedBy = solvedBy;
    }
}