package com.example.demo.repository;

import com.example.demo.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends CrudRepository<TicketRepository,Long> {
    TicketEntity findByStageName(String stageName);
}
