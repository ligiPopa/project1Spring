package com.example.demo.service;

import com.example.demo.dto.TicketDTO;
import com.example.demo.entity.PriceEntity;
import com.example.demo.entity.StageEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.repository.StageRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.impl.TicketServiceImpl;
import com.example.demo.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TicketServiceImplTest {
    @InjectMocks
    TicketServiceImpl ticketService;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    StageRepository stageRepository;

    @Mock
    Utils utils;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void createTicket() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setFullPrice(10.2);
        priceEntity.setChildPrice(5);
        priceEntity.setStudentPrice(10);

        StageEntity stageEntity = new StageEntity();
        stageEntity.setStageName("stageTest");
        stageEntity.setStageId("stageId");
        stageEntity.setPriceDetails(priceEntity);

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPriceType("full");
        ticketEntity.setStageDetails(stageEntity);

        when(stageRepository.findByStageName("Stage1")).thenReturn(stageEntity);
        when(utils.generateId(30)).thenReturn("1234");
        when(ticketRepository.save(any())).thenReturn(ticketEntity);

        TicketDTO ticketDTO = ticketService.createTicket("Stage1");

        assertNotNull(ticketDTO);
        assertEquals("full", ticketDTO.getPriceType());
    }

    @Test
    final void createTicketByPriceType() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setFullPrice(10.2);
        priceEntity.setChildPrice(5);
        priceEntity.setStudentPrice(10);

        StageEntity stageEntity = new StageEntity();
        stageEntity.setStageName("stageTest");
        stageEntity.setStageId("stageId");
        stageEntity.setPriceDetails(priceEntity);

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPriceType("student");
        ticketEntity.setStageDetails(stageEntity);

        when(stageRepository.findByStageName("Stage1")).thenReturn(stageEntity);
        when(utils.generateId(30)).thenReturn("1234");
        when(ticketRepository.save(any())).thenReturn(ticketEntity);

        TicketDTO ticketDTO = ticketService.createTicketByPriceType("student", "Stage1");

        assertNotNull(ticketDTO);
        assertEquals("student", ticketDTO.getPriceType());
    }

    @Test
    final void getTicketByTicketId() {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPriceType("student");
        ticketEntity.setTicketId("ticketId");

        when(ticketRepository.findByTicketId("ticketId")).thenReturn(ticketEntity);

        TicketDTO ticketDTO = ticketService.getTicketByTicketId("ticketId");

        assertNotNull(ticketDTO);
        assertEquals(ticketEntity.getTicketId(), ticketDTO.getTicketId());
    }


    @Test
    final void getTicketByType() {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicketId("ticketId");
        ticketEntity.setType("Type1");

        List<TicketEntity> ticketEntityList = new ArrayList<>();
        ticketEntityList.add(ticketEntity);

        when(ticketRepository.findByType("Type1")).thenReturn(ticketEntityList);

        List<TicketDTO> ticketDTOList = ticketService.getTicketByType("Type1");

        assertNotNull(ticketDTOList);
        assertEquals(ticketEntityList.size(), ticketDTOList.size());
        assertEquals(1, ticketDTOList.size());
    }
}
