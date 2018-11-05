package com.process.processmanagerapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "ProcessOpinion")
@Table(name = "ProcessOpinion")
@AllArgsConstructor
public class ProcessOpinion implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private int processOpinionId;

    @Column
    private String processOpinion;

    @Column
    private Date createDate;

    @Column
    private String createBy;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "originUserId", nullable = false)
    private User user;

    public ProcessOpinion(final String processOpinion, final Date createDate, final String createBy, final User user) {
        this.processOpinion = processOpinion;
        this.createDate = createDate;
        this.createBy = createBy;
        this.user = user;
    }
}
