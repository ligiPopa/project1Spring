package com.example.demo.repository;

import com.example.demo.entity.BandEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BandRepository implements CrudRepository<BandEntity, Long> {
    @Override
    public <S extends BandEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends BandEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BandEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<BandEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<BandEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(BandEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BandEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
