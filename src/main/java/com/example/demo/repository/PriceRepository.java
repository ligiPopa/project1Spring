package com.example.demo.repository;

import com.example.demo.dto.PriceDTO;
import com.example.demo.entity.BandEntity;
import com.example.demo.entity.PriceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository  extends CrudRepository<PriceEntity, Long> {
    PriceDTO findByOfferName(String offerName);
}