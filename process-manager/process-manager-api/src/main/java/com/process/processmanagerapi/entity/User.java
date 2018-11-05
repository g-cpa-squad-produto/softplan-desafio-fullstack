package com.process.processmanagerapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "ProcessUser")
@Table(name = "ProcessUser")
public class User implements Serializable {

    @Id
    @Column
    private String name;

    @Column
    private String password;

    @Column
    private Date createDate;

    @Column
    private String createBy;

    @OneToOne
    @JoinColumn(name = "userTypeId")
    private UserType userType;

    public User(final String name, final String password, final Date createDate, final String createBy, final UserType userType) {
        this.name = name;
        this.password = password;
        this.createDate = createDate;
        this.createBy = createBy;
        this.userType = userType;
    }

}
