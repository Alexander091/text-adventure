package org.my.adventure.questeditor.ejb.beans;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.entities.Node;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by dimko_000 on 07.02.2016.
 */
@Stateless
public class NodeBean {
    @EJB
    NodeDAO nodeDAO;
    public Node getById(Long id) {
        return nodeDAO.getById(id);
    }

    public void saveOrUpdate(Node node) {
         nodeDAO.saveOrUpdate(node);
    }

    public void delete(Node node) {
        nodeDAO.delete(node);
    }

}
