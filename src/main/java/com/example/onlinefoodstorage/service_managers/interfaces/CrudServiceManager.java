package com.example.onlinefoodstorage.service_managers.interfaces;

public interface CrudServiceManager<T,CR,ID> {
    T create(CR request);

    T update(CR request);

    T getById(ID id);

    void delete(ID id);

}
