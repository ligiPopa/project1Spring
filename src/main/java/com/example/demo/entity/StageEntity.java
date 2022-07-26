package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "stage")
public class StageEntity implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;
    @Id
    @GeneratedValue
    private long id;
    @Column(length=30, nullable=false)
    private String stageId;

    @Column(nullable=false, length=120)
    private String stageName;

    @OneToOne
    @JoinColumn(name="price_id")
    private PriceEntity priceDetails;

    @OneToMany(mappedBy="stageDetails", cascade=CascadeType.ALL)
    private List<BandEntity> bands;
    @Column(nullable=false)
    private int stageCapacity;
    private int currentFreePlaces;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<BandEntity> getBands() {
        return bands;
    }

    public void setBands(List<BandEntity> bands) {
        this.bands = bands;
    }

    public int getStageCapacity() {
        return stageCapacity;
    }

    public void setStageCapacity(int stageCapacity) {
        this.stageCapacity = stageCapacity;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public int getCurrentFreePlaces() {
        return currentFreePlaces;
    }

    public void setCurrentFreePlaces(int currentFreePlaces) {
        this.currentFreePlaces = currentFreePlaces;
    }

    public PriceEntity getPriceDetails() {
        return priceDetails;
    }

    public void setPriceDetails(PriceEntity priceDetails) {
        this.priceDetails = priceDetails;
    }
}
