package com.example.demo.model.response;

import java.util.ArrayList;
import java.util.List;

public class BandRest {

    private String name;

    private int nrMembers;

    private List<UserRest> listOfUserDetails = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNrMembers() {
        return nrMembers;
    }

    public void setNrMembers(int nrMembers) {
        this.nrMembers = nrMembers;
    }

    public List<UserRest> getListOfUserDetails() {
        return listOfUserDetails;
    }

    public void setListOfUserDetails(List<UserRest> listOfUserDetails) {
        this.listOfUserDetails = listOfUserDetails;
    }
}
