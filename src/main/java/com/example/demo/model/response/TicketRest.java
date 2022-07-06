package com.example.demo.model.response;

import com.example.demo.dto.StageDTO;

public class TicketRest {
    private String ticketId;
    private String ticketType;
    private String stageName;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageDetails(StageRest stageDetails) {
        this.stageName = stageName;
    }
}
