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
}
