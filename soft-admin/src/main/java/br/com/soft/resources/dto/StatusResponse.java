/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.soft.resources.dto;

/**
 *
 * @author Moquiuti
 */
public enum StatusResponse {

    OK(200, "Ok!"), NOT_FOUND(404, "Not Found!"), BAD_REQUEST(400, "Bad Request!"), SERVER_ERROR(500, "Internal server error!");

    private StatusResponse(Integer codigo, String msg) {
        this.codigo = codigo;
        this.msg = msg;
    }

    private Integer codigo;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

}
