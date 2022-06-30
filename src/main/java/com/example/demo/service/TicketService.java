package com.example.demo.service;

import com.example.demo.dto.TicketDTO;

public interface TicketService {
    //Todo add implementation for this
    //TicketDTO getTicketByStageName(String stageName);

    TicketDTO createTicket(TicketDTO ticket);
    TicketDTO getTicketByTicketId(String ticketId);
}
