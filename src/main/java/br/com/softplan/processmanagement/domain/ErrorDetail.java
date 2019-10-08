package br.com.softplan.processmanagement.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorDetail {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long timeStamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorInfo;

    public ErrorDetail(String title, Long status, Long timeStamp, String errorInfo) {
        this.title = title;
        this.status = status;
        this.timeStamp = timeStamp;
        this.errorInfo = errorInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
