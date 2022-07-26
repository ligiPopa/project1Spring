package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private int age;

    @Value("false")
    @Column(nullable = false)
    private boolean isMemberOfBand;

    @ManyToOne
    @JoinColumn(name = "bands_id")
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

    public void setMemberOfBand(boolean memberOfBand) {
        isMemberOfBand = memberOfBand;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBandDetails(BandEntity bandDetails) {
        this.bandDetails = bandDetails;
    }
}
