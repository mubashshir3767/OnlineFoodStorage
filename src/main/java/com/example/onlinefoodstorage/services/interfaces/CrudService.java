package com.example.onlinefoodstorage.services.interfaces;

public interface Crud<T, ID extends Number, C, U> {
    C create(C entity);

    T getById(ID id);

    C update(U entity);

    void delete(ID id);

}
