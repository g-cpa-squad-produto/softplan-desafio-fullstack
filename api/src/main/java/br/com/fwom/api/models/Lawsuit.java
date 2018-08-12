package br.com.fwom.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedNativeQuery(name = "Lawsuit.findOpenedByUserEmail", query = "SELECT l.* FROM lawsuits l LEFT JOIN lawsuit_users lu ON l.id = lu.lawsuit_id LEFT JOIN users u ON u.id = lu.user_id  WHERE u.email = 'email' AND l.opened = TRUE")
@Table(name = "lawsuits")
public class Lawsuit extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 3, max = 100)
    private String number;

    @NotBlank
    @Size(min = 3, max = 100)
    private String author;

    @Size(max = 100)
    private String defendant;

    @Size(max = 100)
    private String subject;

    private boolean opened;

    @Size(max = 50)
    private String stateClass;

    @Size(max = 100)
    private String stateDescription;

    @Size(max = 100)
    private String stateActor;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String lawyers;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LawsuitUser> lawsuitUsers;

    public List<LawsuitUser> getLawsuitUsers() {
        return lawsuitUsers;
    }

    public void setLawsuitUsers(List<LawsuitUser> lawsuitUsers) {
        this.lawsuitUsers = lawsuitUsers;
    }

    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Long> editorUsers = new ArrayList<>();

    public List<Long> getEditorUsers() {
        return editorUsers;
    }

    public void setEditorUsers(List<Long> editorUsers) {
        this.editorUsers = editorUsers;
    }

    public Lawsuit(@NotBlank @Size(min = 3, max = 100) String number,
                   @NotBlank @Size(min = 3, max = 100) String author,
                   @Size(max = 100) String defendant,
                   @Size(max = 100) String subject,
                   @Size(max = 50) String stateClass,
                   @Size(max = 100) String stateDescription,
                   boolean opened,
                   String stateActor,
                   String description,
                   String lawyers) {
        this.number = number;
        this.author = author;
        this.defendant = defendant;
        this.subject = subject;
        this.stateClass = stateClass;
        this.stateDescription = stateDescription;
        this.stateActor = stateActor;
        this.description = description;
        this.opened = opened;
        this.lawyers = lawyers;
    }

    public Lawsuit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDefendant() {
        return defendant;
    }

    public void setDefendant(String defendant) {
        this.defendant = defendant;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStateClass() {
        return stateClass;
    }

    public void setStateClass(String stateClass) {
        this.stateClass = stateClass;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    public String getStateActor() {
        return stateActor;
    }

    public void setStateActor(String stateActor) {
        this.stateActor = stateActor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLawyers() {
        return lawyers;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public void setLawyers(String lawyers) {
        this.lawyers = lawyers;
    }
}
