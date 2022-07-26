package com.example.demo;

import com.example.demo.dto.TicketDTO;
import com.example.demo.model.request.TicketByTypeRequestModel;
import com.example.demo.model.request.TicketRequestModel;
import com.example.demo.model.response.TicketRest;
import com.example.demo.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    TicketService ticketService;

    // TODO get the ticket by type  -> this method should be change adding as a result a list !!!!!!!
    @GetMapping(path = "/search/byType/{type}")
    public List<TicketRest> getTicketByType(@PathVariable String type) {

          List<TicketRest> tickets = ticketService.getTicketByType(type).stream()
                  .map( e->{
                      TicketRest ticketRest = new TicketRest();
                      BeanUtils.copyProperties(e, ticketRest);
                      return ticketRest;} ).collect(Collectors.toList());
          return tickets;
    }

    @PostMapping(path = "/buy/byStageName")
    public TicketRest buyTicket(@RequestBody TicketRequestModel ticket ) {

        TicketDTO boughtTicket = ticketService.createTicket(ticket.getStageName());

        TicketRest returnValue = new TicketRest();
        BeanUtils.copyProperties(boughtTicket, returnValue);

        //returnValue.setType(boughtTicket.getType());
        //returnValue.setPriceType(boughtTicket.getType());
        returnValue.setStageName(boughtTicket.getStageDetails().getStageName());

        return returnValue;
    }

    @PostMapping(path = "/buy/byType")
    public TicketRest buyTicketByType(@RequestBody TicketByTypeRequestModel ticket ) {

        TicketDTO boughtTicket = ticketService.createTicketByPriceType(ticket.getType(), ticket.getStageName());

        TicketRest returnValue = new TicketRest();
        BeanUtils.copyProperties(boughtTicket, returnValue);

        returnValue.setStageName(boughtTicket.getStageDetails().getStageName());

        return returnValue;
    }
}