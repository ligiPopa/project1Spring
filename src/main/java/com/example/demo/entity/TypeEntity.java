package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="type")
public class TypeEntity implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length=120)
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
