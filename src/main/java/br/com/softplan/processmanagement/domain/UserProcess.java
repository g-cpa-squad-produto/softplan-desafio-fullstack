package br.com.softplan.processmanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="app_user_process")
public class UserProcess implements Serializable {

    private static final long serialVersionUID = -3818490191704957608L;

    @EmbeddedId
    private UserProcessId userProcessId;

    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime createdAt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserProcessId getUserProcessId() {
        return userProcessId;
    }

    public void setUserProcessId(UserProcessId userProcessId) {
        this.userProcessId = userProcessId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
