package com.example.demo.repository;

import com.example.demo.entity.BandEntity;
import com.example.demo.entity.StageEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends CrudRepository<BandEntity, Long> {
    BandEntity findByName(String name);
    BandEntity findByBandId(String bandId);

    List<BandEntity> findByStageDetails(String stageId);
}
