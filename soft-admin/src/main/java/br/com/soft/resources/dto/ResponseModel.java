package br.com.soft.resources.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Moquiuti
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T extends Object> implements Serializable {

    private Integer cod;
    private String message;
    private final List<T> data;
    private Integer page;
    private Integer per_page;
    private Integer total_count;

    public ResponseModel() {
        this.data = new ArrayList<>();
    }

    public ResponseModel(List<T> data) {
        this.data = data;
    }

    public ResponseModel(List<T> data, StatusResponse status) {
        this.data = data;
        this.cod = status.getCodigo();
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cod);
        hash = 17 * hash + Objects.hashCode(this.message);
        hash = 17 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResponseModel<?> other = (ResponseModel<?>) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.cod, other.cod)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    public void setStatus(StatusResponse statusResponse) {
        this.cod = statusResponse.getCodigo();
        this.message = statusResponse.getMsg();
    }

}
