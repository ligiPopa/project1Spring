package com.example.demo.dto;

import java.io.Serializable;

public class TypeDTO implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;

    private long id;

    private String typeId;

    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
