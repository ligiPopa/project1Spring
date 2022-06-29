package com.example.demo.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 7809200551672852690L;

    private long id;

    private String userId;

    private String email;

    private int age;

    private boolean isMemberOfBand;

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

    public String getUserId() {
        return userId;
    }

    public boolean isMemberOfBand() {
        return isMemberOfBand;
    }

    public boolean isIdBand() {
        return idBand;
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
}
