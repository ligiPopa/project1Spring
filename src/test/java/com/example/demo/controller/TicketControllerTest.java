package com.example.demo.controller;

import com.example.demo.TicketController;
import com.example.demo.dto.StageDTO;
import com.example.demo.dto.TicketDTO;
import com.example.demo.model.request.TicketByTypeRequestModel;
import com.example.demo.model.request.TicketRequestModel;
import com.example.demo.model.response.TicketRest;
import com.example.demo.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class TicketControllerTest {
    @InjectMocks
    TicketController ticketController;

    @Mock
    TicketServiceImpl ticketService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void getTicketByType(){
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setType("Type1");
        ticketDTO.setTicketId("ticketId");

        when(ticketService.getTicketByType("Type1")).thenReturn(Arrays.asList(ticketDTO));

        List<TicketRest> ticketRestList = ticketController.getTicketByType("Type1");

        assertNotNull(ticketRestList);
        assertEquals(ticketDTO.getType(), ticketRestList.get(0).getType());
        assertEquals(ticketDTO.getTicketId(), ticketRestList.get(0).getTicketId());
    }

    @Test
    final void buyTicket(){
        TicketRequestModel ticketRequestModel = new TicketRequestModel();
        ticketRequestModel.setStageName("stageTest");

        StageDTO stageDTO= new StageDTO();
        stageDTO.setStageName("stageTest");

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId("ticketId");
        ticketDTO.setType("ticketType");
        ticketDTO.setPriceType("student");
        ticketDTO.setStageDetails(stageDTO);

        TicketRest ticketRest = new TicketRest();
        ticketRest.setTicketId(ticketDTO.getTicketId());
        ticketRest.setStageName(ticketDTO.getStageDetails().getStageName());
        ticketRest.setType(ticketDTO.getType());

        when(ticketService.createTicket(ticketRequestModel.getStageName())).thenReturn(ticketDTO);

        TicketRest ticketResult = ticketController.buyTicket(ticketRequestModel);
        assertNotNull(ticketResult);
        assertEquals(ticketDTO.getTicketId(), ticketResult.getTicketId());
        assertEquals(ticketDTO.getType(), ticketResult.getType());
        assertEquals(ticketDTO.getPriceType(), ticketResult.getPriceType());
        assertEquals(ticketDTO.getStageDetails().getStageName(), ticketResult.getStageName());
    }

    @Test
    final void buyTicketByType(){
        TicketByTypeRequestModel ticketByTypeRequestModel = new TicketByTypeRequestModel();
        ticketByTypeRequestModel.setType("Type1");
        ticketByTypeRequestModel.setStageName("stageTest");

        StageDTO stageDTO= new StageDTO();
        stageDTO.setStageName("stageTest");

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId("ticketId");
        ticketDTO.setType("ticketType");
        ticketDTO.setPriceType("student");
        ticketDTO.setStageDetails(stageDTO);

        when(ticketService.createTicketByPriceType(ticketByTypeRequestModel.getType(), ticketByTypeRequestModel.getStageName())).thenReturn(ticketDTO);

        TicketRest ticketRest = ticketController.buyTicketByType(ticketByTypeRequestModel);

        assertNotNull(ticketRest);
        assertEquals(ticketDTO.getType(), ticketRest.getType());
        assertEquals(ticketDTO.getTicketId(), ticketRest.getTicketId());
        assertEquals(ticketDTO.getTicketId(), ticketRest.getTicketId());
    }
}
