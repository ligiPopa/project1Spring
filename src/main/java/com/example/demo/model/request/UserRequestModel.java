package com.example.demo.model.request;

public class UserRequestModel {

    //private String userId;

    private String email;

    private int age;

    private boolean isMemberOfBand;

    private String bandName;

   // private boolean idBand;

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }


    public String getBandName() {
        return bandName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMemberOfBand() {
        return isMemberOfBand;
    }

    public void setMemberOfBand(boolean memberOfBand) {
        isMemberOfBand = memberOfBand;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    //    public boolean isIdBand() {
//        return idBand;
//    }
//
//    public void setIdBand(boolean idBand) {
//        this.idBand = idBand;
//    }
}
