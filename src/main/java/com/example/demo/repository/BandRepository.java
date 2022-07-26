package com.example.demo.repository;

import com.example.demo.entity.BandEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends CrudRepository<BandEntity, Long> {
    BandEntity findByName(String name);
    BandEntity findByBandId(String bandId);

    //@Query(value= "select * from bands stageDetails.id where bands.stageDetails.id  = :stageId", nativeQuery=true)
    List<BandEntity> findAll();
}
