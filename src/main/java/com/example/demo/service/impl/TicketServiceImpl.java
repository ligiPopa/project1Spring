package com.example.demo.service.impl;

import com.example.demo.dto.StageDTO;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    StageRepository stageRepository;

    @Autowired
    Utils utils;

    //the ticket is set for a stage with full price
    @Override
    public TicketDTO createTicket(String stageName) {
//        TicketEntity foundTicketEntity = ticketRepository.findByTicketId(ticket.getTicketId()
//        if (foundTicketEntity != null )
//            throw new UserServiceException("Record already exists");

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setStageDetails(stageRepository.findByStageName(stageName));
        ticketEntity.setType(StagesAndTypes.valueOf(stageName).label);
        ticketEntity.setPriceType("full");
        setFieldsForTicketEntity(ticketEntity, stageName, null);

        TicketEntity storedTicketDetails = ticketRepository.save(ticketEntity);

        TicketDTO returnValue = new TicketDTO();
        StageDTO stageDTO = new StageDTO();
        BeanUtils.copyProperties(storedTicketDetails, returnValue);
        BeanUtils.copyProperties(storedTicketDetails.getStageDetails(), stageDTO);
        returnValue.setStageDetails(stageDTO);
        return returnValue;
    }

    //the ticket is set by stage and price type
    @Override
    public TicketDTO createTicketByPriceType(String priceType, String stageName) {
//        TicketEntity foundTicketEntity = ticketRepository.findByTicketId(ticket.getTicketId()
//        if (foundTicketEntity != null )
//            throw new UserServiceException("Record already exists");

        TicketEntity ticketEntity = new TicketEntity();

        setFieldsForTicketEntity(ticketEntity, stageName, priceType);
        ticketEntity.setStageDetails(stageRepository.findByStageName(stageName));
        ticketEntity.setType(StagesAndTypes.valueOf(stageName).label);

        TicketEntity storedTicketDetails = ticketRepository.save(ticketEntity);

        StageDTO stageDTO = new StageDTO();
        BeanUtils.copyProperties(storedTicketDetails.getStageDetails(), stageDTO);

        TicketDTO returnValue = new TicketDTO();
        BeanUtils.copyProperties(storedTicketDetails, returnValue);
        returnValue.setStageDetails(stageDTO);

        return returnValue;
    }

    @Override
    public TicketDTO getTicketByTicketId(String ticketId) {

        TicketDTO returnValue = new TicketDTO();

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
    public List<TicketDTO> getTicketByType(String typeName) {

        List<TicketDTO> ticketEntityList = ticketRepository.findByType(typeName).stream()
                .map( e->{
                    TicketDTO ticketDTO = new TicketDTO();
                    BeanUtils.copyProperties(e, ticketDTO);
                    return ticketDTO;} ).collect(Collectors.toList());

        if(ticketEntityList == null)
            throw new UserServiceException( "Ticket with type"  + typeName + " is not found");

        return ticketEntityList;
    }

    //by default the price type is set as full price
    private void setFieldsForTicketEntity(TicketEntity ticketEntity, String stageName, String typeByPrice){
        StageEntity foundStageEntity = stageRepository.findByStageName(stageName);

        String publicTicketId = utils.generateId(30);

        ticketEntity.setTicketId(publicTicketId);

        if (foundStageEntity.getCurrentFreePlaces() > 0 )
            foundStageEntity.setCurrentFreePlaces(foundStageEntity.getCurrentFreePlaces() -1 );
        else
            foundStageEntity.setCurrentFreePlaces(0);

        //TODO optimize this code part !!!!!
        if (typeByPrice == null || typeByPrice.equalsIgnoreCase("full")) {
            ticketEntity.setPrice(foundStageEntity.getPriceDetails().getFullPrice());
            ticketEntity.setPriceType("full");
        }
        else
        {
            if (typeByPrice.equalsIgnoreCase("student")) {
                ticketEntity.setPrice(foundStageEntity.getPriceDetails().getStudentPrice());
                ticketEntity.setPriceType("student");
            }
            else{
            ticketEntity.setPrice(foundStageEntity.getPriceDetails().getChildPrice());
            ticketEntity.setPriceType("child");
        }
        }
        ticketEntity.setStageDetails(foundStageEntity);
    }
}
