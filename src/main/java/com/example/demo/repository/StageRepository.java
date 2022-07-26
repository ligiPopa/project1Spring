package com.example.demo.repository;

import com.example.demo.entity.StageEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends CrudRepository<StageEntity,Long> {
    StageEntity findByStageName(String stageName);
    StageEntity findByStageId(String stagId);
    List<StageEntity> findAll();
}
