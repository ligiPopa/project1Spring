package com.example.demo.repository;

import com.example.demo.entity.BandEntity;
import com.example.demo.entity.StageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends CrudRepository<BandEntity, Long> {
    BandEntity findByBandName(String bandName);
    BandEntity findByBandId(String bandId);
}
