package com.example.demo.dto;

import java.io.Serializable;

public class BandDTO implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;
    private long id;
    private String name;

    private int nrMembers;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNrMembers() {
        return nrMembers;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNrMembers(int nrMembers) {
        this.nrMembers = nrMembers;
    }
}
