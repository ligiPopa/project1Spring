package com.example.demo.model.request;

import com.example.demo.dto.PriceDTO;

public class StageCreateRequestModel extends StageBasicRequestModel {
    private int stageCapacity;

    private PriceDTO price;

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
