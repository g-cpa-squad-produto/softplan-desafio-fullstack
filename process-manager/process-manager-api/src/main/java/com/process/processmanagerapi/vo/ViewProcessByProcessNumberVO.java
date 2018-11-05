package com.process.processmanagerapi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ViewProcessByProcessNumberVO implements Serializable {

    private int processNumber;

    private String viewBy;

}
