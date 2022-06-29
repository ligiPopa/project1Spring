package com.example.demo.dto;

import java.io.Serializable;

public class TicketDTO implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;
    private long id;

    private String ticketId;
    private String ticketType;

    private StageDTO stageDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public StageDTO getStageDetails() {
        return stageDetails;
    }

    public void setStageDetails(StageDTO stageDetails) {
        this.stageDetails = stageDetails;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
