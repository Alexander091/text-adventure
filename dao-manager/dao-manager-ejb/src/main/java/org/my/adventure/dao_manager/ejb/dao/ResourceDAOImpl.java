package org.my.adventure.dao_manager.ejb.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Resource;
import org.my.adventure.dao_manager.api.entities.TypeOfResource;

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
    public List<Resource> getResources(Long questId, Long typeOfResourceId) {
        Session session = null;
        List<Resource> resources = null;
        try {
            session = sessionFactory.openSession();
            String hql = "FROM org.my.adventure.dao_manager.api.entities.Resource as R WHERE R.questByQuestId.id = :id and R.type.id = :tid";
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
}
