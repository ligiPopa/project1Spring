package com.example.demo.repository;

import com.example.demo.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity,Long> {
    TicketEntity findByTicketId(String ticketId);
    List<TicketEntity> findByType(String type);
}
