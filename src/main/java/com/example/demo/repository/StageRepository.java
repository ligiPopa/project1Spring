package com.example.demo.repository;

import com.example.demo.entity.StageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends CrudRepository<StageEntity,Long> {
    StageEntity findByStageName(String stageName);
    StageEntity findByStageId(String stagId);
}
