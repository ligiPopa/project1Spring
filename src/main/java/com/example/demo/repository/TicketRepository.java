package com.example.demo.repository;

import com.example.demo.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity,Long> {
    TicketEntity findByTicketId(String ticketId);
    TicketEntity findByType(String type);
}
