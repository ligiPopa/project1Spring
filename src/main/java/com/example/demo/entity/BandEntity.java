package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="band")
public class BandEntity implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
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
}
