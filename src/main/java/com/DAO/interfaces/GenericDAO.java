package com.DAO.interfaces;

import java.util.List;

public interface GenericDAO<T> {

    T findById(long id);

    List<T> findAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
