package com.example.demo.model.request;

public class StageCreateRequestModel extends StageBasicRequestModel {
    private int stageCapacity;

    public int getStageCapacity() {
        return stageCapacity;
    }

    public void setStageCapacity(int stageCapacity) {
        this.stageCapacity = stageCapacity;
    }
}
