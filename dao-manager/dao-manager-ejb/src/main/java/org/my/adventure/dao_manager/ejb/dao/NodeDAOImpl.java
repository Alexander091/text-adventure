package org.my.adventure.dao_manager.ejb.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.entities.Node;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Remote(NodeDAO.class)
public class NodeDAOImpl extends CommonDAOImpl<Node> implements NodeDAO{
    public List<Node> getNodesByQuestId(long questId) {
        Session session = sessionFactory.openSession();
        String hql = "FROM org.my.adventure.dao_manager.api.entities.Node as N WHERE N.questByQuestId.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", questId);
        List<Node> result = query.list();
        session.close();
        return result;
    }
}
