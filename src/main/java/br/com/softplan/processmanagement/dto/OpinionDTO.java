package br.com.softplan.processmanagement.dto;

import java.time.LocalDateTime;

public class OpinionDTO {

    private Long idProcess;
    private Long idUser;
    private String text;
    private LocalDateTime createdAt;

    public Long getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Long idProcess) {
        this.idProcess = idProcess;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "OpinionDTO{" + "idProcess=" + idProcess + ", idUser=" + idUser + ", text='" + text + '\'' + ", createdAt=" + createdAt + '}';
    }
}
