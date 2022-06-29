package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="person")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private int age;

    @Column(nullable = false)
    private boolean isMemberOfBand;

    @Column
    private boolean idBand;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public boolean isMemberOfBand() {
        return isMemberOfBand;
    }

    public boolean isIdBand() {
        return idBand;
    }
}
