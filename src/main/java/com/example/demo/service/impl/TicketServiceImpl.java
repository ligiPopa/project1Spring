package com.example.demo.service.impl;

import com.example.demo.dto.TicketDTO;
import com.example.demo.entity.TicketEntity;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import com.example.demo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    Utils utils;

    @Override
    public TicketDTO createTicket(TicketDTO ticket) {

        if (ticketRepository.findByTicketId(ticket.getTicketId()) != null )
            throw new UserServiceException("Record already exists");


        TicketEntity ticketEntity = new TicketEntity();
        BeanUtils.copyProperties(ticket, ticketEntity);

        String publicTicketId = utils.generateUserId(30);

        ticketEntity.setTicketId(publicTicketId);

        TicketEntity storedTicketDetails = ticketRepository.save(ticketEntity);

        TicketDTO returnValue = null;
        BeanUtils.copyProperties(storedTicketDetails, returnValue);

        return returnValue;
    }

    @Override
    public TicketDTO getTicketByTicketId(String ticketId) {

        TicketDTO returnValue = null;

        TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketId);

        if(ticketEntity != null)
        {
            BeanUtils.copyProperties(ticketEntity, returnValue);
        }
        else
            throw new UserServiceException( "Ticket with id"  + ticketId + " is not found");

        return returnValue;
    }
}
