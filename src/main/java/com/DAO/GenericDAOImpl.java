package com.DAO;

import com.DAO.interfaces.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;


@Repository
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getCurrentSession().createQuery("FROM " + clazz.getName()).list();
    }

    @Override
    public void save(T t) {
        getCurrentSession().save(t);
    }

    @Override
    public void update(T t) {
        getCurrentSession().update(t);

    }

    @Override
    public void delete(T t) {
        sessionFactory.getCurrentSession().delete(t);
    }


    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
