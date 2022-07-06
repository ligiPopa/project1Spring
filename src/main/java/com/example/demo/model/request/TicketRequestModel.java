package com.example.demo.model.request;

import com.example.demo.model.response.StageRest;

public class TicketRequestModel {
    private String stageName;

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}
