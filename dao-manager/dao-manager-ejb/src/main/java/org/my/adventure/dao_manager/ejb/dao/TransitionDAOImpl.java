package org.my.adventure.dao_manager.ejb.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.my.adventure.dao_manager.api.dao.TransitionDAO;
import org.my.adventure.dao_manager.api.entities.Transition;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Remote(TransitionDAO.class)
public class TransitionDAOImpl extends CommonDAOImpl<Transition> implements TransitionDAO{
    public List<Transition> getTransitionsByNodeId(long nodeId) {
        Session session = sessionFactory.openSession();
        String hql = "FROM org.my.adventure.dao_manager.api.entities.Transition as T WHERE T.nodeByFromNode.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", nodeId);
        List<Transition> result = query.list();
        session.close();
        return result;
    }
}
