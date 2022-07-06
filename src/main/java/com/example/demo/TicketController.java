package com.example.demo;

import com.example.demo.dto.TicketDTO;
import com.example.demo.model.request.TicketRequestModel;
import com.example.demo.model.response.TicketRest;
import com.example.demo.resources.StagesAndTypes;
import com.example.demo.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    TicketService ticketService;

    // TODO get the ticket by type  -> this method should be change adding asa a result a list !!!!!!!
    @GetMapping(path = "/search/byType/{type}")
    public TicketRest getTicketByType(@PathVariable String type) {

          TicketDTO ticket = ticketService.getTicketByType(type);
          TicketRest returnValue = new TicketRest();

          BeanUtils.copyProperties(ticket, returnValue);

          return returnValue;
    }

    @PostMapping(path = "/buy")
    public TicketRest buyTicket(@RequestBody TicketRequestModel ticket ) {

        TicketDTO boughtTicket = ticketService.createTicket(ticket.getStageName());

        TicketRest returnValue = new TicketRest();
        BeanUtils.copyProperties(boughtTicket, returnValue);

        return returnValue;
    }
}
