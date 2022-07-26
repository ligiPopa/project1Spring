package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String email);
    @Query(value= "select * from users u where u.is_member_of_band = '0'", nativeQuery=true)
    List<UserEntity> findAllSingers();

    @Query(value= "select * from users u where u.is_member_of_band = '1'", nativeQuery=true)
    List<UserEntity> findAllBands();
}
