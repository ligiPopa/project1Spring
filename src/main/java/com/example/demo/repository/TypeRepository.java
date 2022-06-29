package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TypeRepository  implements CrudRepository<TypeRepository, Long> {
    @Override
    public <S extends TypeRepository> S save(S entity) {
        return null;
    }

    @Override
    public <S extends TypeRepository> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TypeRepository> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TypeRepository> findAll() {
        return null;
    }

    @Override
    public Iterable<TypeRepository> findAllById(Iterable<Long> longs) {
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
    public void delete(TypeRepository entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends TypeRepository> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
