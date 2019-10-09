package br.com.softplan.processmanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="app_usersystem_process")
public class UserSystemProcess implements Serializable {

    private static final long serialVersionUID = -3818490191704957608L;

    @EmbeddedId
    private UserSystemProcessId userSystemProcessId;

    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime createdAt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserSystemProcessId getUserSystemProcessId() {
        return userSystemProcessId;
    }

    public void setUserSystemProcessId(UserSystemProcessId userSystemProcessId) {
        this.userSystemProcessId = userSystemProcessId;
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
