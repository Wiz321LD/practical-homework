package org.example.hw4.service;

import java.util.List;

public interface SimpleService<K, E> {

    //CREATE
    E create(E element);

    //READ one
    E findById(K id);

    //READ all
    List<E> findAll();

    //UPDATE
    void update(E element);

    //DELETE
    void delete(K id);

}
