package com.example.demo.model.response;

import com.example.demo.dto.PriceDTO;

import java.util.ArrayList;
import java.util.List;

public class StageRest {
    private String stageId;
    private String stageName;
    private List<BandRest> bands = new ArrayList<>();
    private int stageCapacity;

    private PriceDTO price;

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<BandRest> getBands() {
        return bands;
    }

    public void setBands(List<BandRest> bands) {
        this.bands = bands;
    }

    public int getStageCapacity() {
        return stageCapacity;
    }

    public void setStageCapacity(int stageCapacity) {
        this.stageCapacity = stageCapacity;
    }

    public PriceDTO getPrice() {
        return price;
    }

    public void setPrice(PriceDTO price) {
        this.price = price;
    }
}
