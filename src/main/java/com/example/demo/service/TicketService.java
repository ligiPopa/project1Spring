package com.example.demo.service;

import com.example.demo.dto.TicketDTO;

public interface TicketService {
    TicketDTO getTicketByStageName(String stageName);
}
