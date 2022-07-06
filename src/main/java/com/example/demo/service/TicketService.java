package com.example.demo.service;

import com.example.demo.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    TicketDTO createTicket(String stageName);
    TicketDTO getTicketByTicketId(String ticketId);

    //TODO change with list
    TicketDTO getTicketByType(String typeName);
}
