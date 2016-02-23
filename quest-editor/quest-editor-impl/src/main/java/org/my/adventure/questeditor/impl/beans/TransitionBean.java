package org.my.adventure.questeditor.impl.beans;

import org.my.adventure.dao_manager.api.dao.TransitionDAO;
import org.my.adventure.dao_manager.api.entities.Transition;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by dimko_000 on 19.02.2016.
 */
@Stateless
public class TransitionBean {
    @EJB
    TransitionDAO transitionDAO;
    public Transition getById(Long id) {
        return transitionDAO.getById(id);
    }

    public void saveOrUpdate(Transition transition) {
        transitionDAO.saveOrUpdate(transition);
    }

    public void delete(Transition transition) {
        transitionDAO.delete(transition);
    }
}
