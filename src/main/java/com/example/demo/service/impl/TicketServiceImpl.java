package com.example.demo.service.impl;

import com.example.demo.dto.TicketDTO;
import com.example.demo.entity.StageEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.StageRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.resources.StagesAndTypes;
import com.example.demo.service.TicketService;
import com.example.demo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    StageRepository stageRepository;

    @Autowired
    Utils utils;

    @Override
    public TicketDTO createTicket(String stageName) {
//        TicketEntity foundTicketEntity = ticketRepository.findByTicketId(ticket.getTicketId()
//        if (foundTicketEntity != null )
//            throw new UserServiceException("Record already exists");

        StageEntity foundStageEntity = stageRepository.findByStageName(stageName);

        TicketEntity ticketEntity = new TicketEntity();

        ticketEntity.setType(StagesAndTypes.valueOf(stageName).label);


        String publicTicketId = utils.generateId(30);

        ticketEntity.setTicketId(publicTicketId);

        if (foundStageEntity.getCurrentFreePlaces() > 0 )
            foundStageEntity.setCurrentFreePlaces(foundStageEntity.getCurrentFreePlaces() -1 );
        else
            foundStageEntity.setCurrentFreePlaces(0);
        //ticketEntity.setStageDetails(foundStageEntity);


        //ticketEntity.setStageDetails(new StageEntity());
        TicketEntity storedTicketDetails = ticketRepository.save(ticketEntity);

        TicketDTO returnValue = new TicketDTO();
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

    @Override
    public TicketDTO getTicketByType(String typeName) {
        TicketDTO returnValue = null;

        TicketEntity ticketEntity = ticketRepository.findByType(typeName);

        if(ticketEntity != null)
        {
            BeanUtils.copyProperties(ticketEntity, returnValue);
        }
        else
            throw new UserServiceException( "Ticket with type"  + typeName + " is not found");

        return returnValue;
    }
}
