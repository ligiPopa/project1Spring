package com.example.demo.repository;

import com.example.demo.entity.TypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeRepository extends CrudRepository<TypeEntity, Long> {
    TypeEntity findByTypeId(String typeId);
    TypeEntity findByTypeName(String typeName);

}
