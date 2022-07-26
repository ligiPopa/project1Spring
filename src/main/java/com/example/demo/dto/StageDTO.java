package com.example.demo.dto;

import com.example.demo.entity.BandEntity;
import com.example.demo.entity.PriceEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StageDTO implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;
    private long id;
    private String stageId;
    private String stageName;

    private List<BandEntity> bands = new ArrayList<>();

    private int stageCapacity;

    private PriceEntity price;

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

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }
}
