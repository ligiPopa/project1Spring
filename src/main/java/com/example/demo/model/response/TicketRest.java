package com.example.demo.model.response;

import com.example.demo.dto.StageDTO;

public class TicketRest {
    private String ticketId;
    private String type;
    private String stageName;

    private String priceType;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public void setStageDetails(StageRest stageDetails) {
        this.stageName = stageName;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }
}
