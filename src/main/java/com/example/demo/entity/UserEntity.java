package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name="users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private String userId;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private int age;

    @Column(nullable = false)
    private boolean isMemberOfBand;

    @Column
    private boolean idBand;

    @ManyToOne
    @JoinColumn(name="band_id")
    private BandEntity bandDetails;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getUserId() {
        return userId;
    }


    public boolean isMemberOfBand() {
        return isMemberOfBand;
    }

    public boolean isIdBand() {
        return idBand;
    }

    public BandEntity getBandDetails() {
        return bandDetails;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIsMemberOfBand(boolean memberOfBand) {
        isMemberOfBand = memberOfBand;
    }

    public void setIdBand(boolean idBand) {
        this.idBand = idBand;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBandDetails(BandEntity bandDetails) {
        this.bandDetails = bandDetails;
    }
}
