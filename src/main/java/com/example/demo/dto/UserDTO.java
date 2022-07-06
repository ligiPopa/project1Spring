package com.example.demo.dto;

import com.example.demo.entity.BandEntity;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 7809200551672852690L;

    private long id;

    private String userId;

    private String email;

    private int age;

    private boolean isMemberOfBand;

    private BandEntity bandDetails = new BandEntity();
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
