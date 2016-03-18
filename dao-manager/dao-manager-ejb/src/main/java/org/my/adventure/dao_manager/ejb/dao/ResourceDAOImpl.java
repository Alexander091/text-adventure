package org.my.adventure.dao_manager.ejb.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.entities.Resource;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */

@Stateless
@Local(ResourceDAO.class)
public class ResourceDAOImpl extends CommonDAOImpl<Resource>{

    private final Logger log = LogManager.getLogger(ResourceDAOImpl.class);

    public List<Resource> getResources(Long questId, Long typeOfResourceId) {
        Session session = null;
        List<Resource> resources = null;
        try {
            session = sessionFactory.openSession();
            String hql = "FROM org.my.adventure.dao_manager.api.entities.Resource as R " +
                    "WHERE R.questByQuestId.id = :id and R.type.id = :tid order by R.id desc ";
            Query query = session.createQuery(hql);
            query.setParameter("id", questId);
            query.setParameter("tid", typeOfResourceId);
            resources = query.list();
        }
        finally {
            if(session!=null)
                session.close();
        }
        return resources;
    }
    public List<Resource> getPartialResources(Long questId, Long typeOfResourceId) {
        Session session = null;
        List<Resource> resources = null;
        List<Object> objects = null;
        try {
            session = sessionFactory.openSession();
            String hql = "select R.id, R.name FROM org.my.adventure.dao_manager.api.entities.Resource as R " +
                    "WHERE R.questByQuestId.id = :id and R.type.id = :tid order by R.id desc ";
            Query query = session.createQuery(hql);
            query.setParameter("id", questId);
            query.setParameter("tid", typeOfResourceId);
            objects = query.list();
            resources = new ArrayList<>();
            for(Object object : objects) {
                Object[] temp = (Object[]) object;
                Resource resource = new Resource();
                Long id = Long.parseLong(temp[0].toString());
                String name = temp[1].toString();
                resource.setId(id);
                resource.setName(name);
                resources.add(resource);
            }
        }
        finally {
            if(session!=null)
                session.close();
        }
        return resources;
    }
    public void deleteById(Long resourceId) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = "delete from org.my.adventure.dao_manager.api.entities.Resource where id= :id";
            Query query = session.createQuery(hql);
            log.debug("deleting resource with id = " + resourceId);
            query.setLong("id", resourceId);
            query.executeUpdate();
            log.debug("resource with id = " + resourceId + " was removed");
            transaction.commit();
            session.close();
        } catch (Throwable t) {
            transaction.rollback();
            log.error(t.getMessage(), t);
            session.close();
        }
    }
}
