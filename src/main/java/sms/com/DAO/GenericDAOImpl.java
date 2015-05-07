package sms.com.DAO;

import sms.com.DAO.interfaces.GenericDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@Repository
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    protected Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public T findById(long id) {
        return this.entityManager.find(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) this.entityManager.createQuery("FROM " + clazz.getName()).getResultList();
    }

    @Override
    public void save(T t) {
        this.entityManager.persist(t);
    }

    @Override
    public void update(T t) {
        this.entityManager.merge(t);

    }

    @Override
    public void delete(T t) {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
    }


}
