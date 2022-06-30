package com.example.demo.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name="bands")
public class BandEntity implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy="bandDetails", cascade=CascadeType.ALL)
    private List<UserEntity> listOfUserDetails;

    @Column(nullable=false)
    private String bandId;

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

    public String getBandId() {
        return bandId;
    }

    public List<UserEntity> getListOfUserDetails() {
        return listOfUserDetails;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNrMembers(int nrMembers) {
        this.nrMembers = nrMembers;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public void setListOfUserDetails(List<UserEntity> listOfUserDetails) {
        this.listOfUserDetails = listOfUserDetails;
    }
}
