package com.example.demo.service.impl;

import com.example.demo.dto.TicketDTO;
import com.example.demo.entity.TicketEntity;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public TicketDTO getTicketByStageName(String stageName) {

        TicketDTO returnValue = null;

        TicketEntity stageEntity = ticketRepository.findByStageName(stageName);

        if(stageEntity != null)
        {
            BeanUtils.copyProperties(stageEntity, returnValue);
        }

        return returnValue;
    }
}
