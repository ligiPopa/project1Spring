package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="person")
public class PersonEntity implements Serializable {

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
}
