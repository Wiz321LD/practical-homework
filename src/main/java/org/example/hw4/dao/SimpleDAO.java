package org.example.hw4.dao;

import java.util.List;
import java.util.Optional;

public interface SimpleDAO <K, E>{

    //CREATE
    E save(E element);

    //READ one
    Optional<E> findById(K id);

    //READ all
    List<E> findAll();

    //UPDATE
    void update(E element);

    //DELETE
    void delete(K id);

}
