package br.com.fwom.api.models;

import javax.persistence.*;

@Entity
@Table(name = "lawsuit_user")
public class LawsuitUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "lawsuit_id")
    private Long lawsuitId;

    public LawsuitUser(Long userId, Long lawsuitId) {
        this.userId = userId;
        this.lawsuitId = lawsuitId;
    }

    public LawsuitUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLawsuitId() {
        return lawsuitId;
    }

    public void setLawsuitId(Long lawsuitId) {
        this.lawsuitId = lawsuitId;
    }
}