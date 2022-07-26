package com.example.demo.dto;

import java.io.Serializable;

public class TicketDTO implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;
    private long id;

    private String ticketId;
    private String type;

    private double price;

    private String priceType;

    private StageDTO stageDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String ticketType) {
        this.type = ticketType;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }
}
