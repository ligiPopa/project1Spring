package com.example.demo.entity;


import com.example.demo.dto.BandDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="bands")
public class BandEntity implements Serializable {
    //private static final long serialVersionUID = 7809200551672852690L;
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="stage_id")
    private StageEntity stageDetails;

    @OneToMany(mappedBy="bandDetails",fetch = FetchType.LAZY)
    private List<UserEntity> listOfUserDetails;

    @Column(nullable=false)
    private String bandId;

    @Column(nullable=false, unique=true)
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

    public StageEntity getStageDetails() {
        return stageDetails;
    }

    public void setStageDetails(StageEntity stageDetails) {
        this.stageDetails = stageDetails;
    }

    public void setBandDetails(BandEntity bandEntity){
        this.id = bandEntity.getId();
        this.bandId = bandEntity.getBandId();
        this.name = bandEntity.getName();
        this.nrMembers = bandEntity.getNrMembers();
    }
}
