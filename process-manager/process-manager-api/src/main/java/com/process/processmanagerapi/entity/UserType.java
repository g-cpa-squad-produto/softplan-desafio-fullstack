package com.process.processmanagerapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "UserType")
@Table(name = "UserType")
@AllArgsConstructor
public class UserType implements Serializable {

    @Id
    @Column
    private int userTypeId;

    @Column
    private String userTypeName;

    public UserType(final String userTypeName) {
        this.userTypeName = userTypeName;
    }

}
