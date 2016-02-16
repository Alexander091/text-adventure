package org.my.adventure.dao_manager.ejb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.my.adventure.dao_manager.api.dao.CommonDAO;
import org.my.adventure.dao_manager.api.entities.Common;
import org.my.adventure.dao_manager.impl.configurations.HibernateUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public abstract class CommonDAOImpl<T extends Common> implements CommonDAO<T> {
    private Class<T> type;
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public CommonDAOImpl(){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }
    public T getById(Long id){
        Session session = sessionFactory.openSession();
        T result = (T) session.get(type, id);
        session.close();
        return result;
    }

    public void saveOrUpdate(Common object){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    public List<T> getAll(){
        Session session = sessionFactory.openSession();
        List<T> result = session.createCriteria(type).list();
        session.close();
        return result;
    }

    public void delete(Common object){
        Session session = sessionFactory.openSession();
        session.delete(object);
        session.flush();
        session.close();
    }
}
