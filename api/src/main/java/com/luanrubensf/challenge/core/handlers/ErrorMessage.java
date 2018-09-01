package com.luanrubensf.challenge.core.handlers;

public class ErrorMessage {

    private String message;

    private Integer statusCode;

    private String reason;

    public ErrorMessage() {
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ErrorMessage(String message, Integer statusCode, String reason) {
        this.message = message;
        this.statusCode = statusCode;
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
