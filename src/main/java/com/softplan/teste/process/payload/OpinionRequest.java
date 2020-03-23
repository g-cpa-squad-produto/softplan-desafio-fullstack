package com.softplan.teste.process.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OpinionRequest {
    @NotBlank
    @Size(max = 40)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}