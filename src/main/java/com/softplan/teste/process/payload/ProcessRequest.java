package com.softplan.teste.process.payload;

import com.softplan.teste.process.model.User;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProcessRequest {
    @NotBlank
    @Size(max = 140)
    private String question;
    @Size(max = 10)
    private String status;
    @NotNull
    @Size(min = 2, max = 6)
    @Valid
    private List<OpinionRequest> opinions;

    @NotNull
    @Valid
    private ProcessLength processLength;

    @Size(min = 0)
    @Valid
    private List<User> reviewers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<OpinionRequest> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<OpinionRequest> opinions) {
        this.opinions = opinions;
    }

    public ProcessLength getProcessLength() {
        return processLength;
    }

    public void setProcessLength(ProcessLength processLength) {
        this.processLength = processLength;
    }

    public List<User> getReviewers() {
        return reviewers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
