package com.example.demo.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ticket")
public class TicketEntity implements Serializable {
    private static final long serialVersionUID = 7809200551672852690L;
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length=120)
    private String ticketId;

    @Column(nullable=false, length=120)
    private String type;

    @Column(nullable=false)
    private double price;

    @Column(nullable = false)
    private String priceType;

    @OneToOne
    @JoinColumn(name="stage_id")
    private StageEntity stageDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StageEntity getStageDetails() {
        return stageDetails;
    }

    public void setStageDetails(StageEntity stageDetails) {
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
