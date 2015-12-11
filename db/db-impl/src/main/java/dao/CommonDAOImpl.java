package dao;

import configurations.HibernateUtil;
import entities.CommonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public class CommonDAOImpl<T extends CommonEntity> implements CommonDAO<T>{
    private Class<T> type;
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public CommonDAOImpl(){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }
    public T get(Long id){
        Session session = sessionFactory.openSession();
        T result = (T) session.get(type, id);
        session.close();
        return result;
    }

    public void saveOrUpdate(T object){
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(object);
        session.flush();
        session.close();
    }

    public List<T> list(){
        Session session = sessionFactory.openSession();
        List<T> result = session.createCriteria(type).list();
        session.close();
        return result;
    }
}
