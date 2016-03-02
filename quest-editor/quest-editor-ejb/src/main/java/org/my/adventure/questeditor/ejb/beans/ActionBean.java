package org.my.adventure.questeditor.ejb.beans;

import org.my.adventure.dao_manager.api.dao.ActionDAO;
import org.my.adventure.dao_manager.api.entities.Action;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by dimko_000 on 02.03.2016.
 */
@Stateless
public class ActionBean extends CommonBean<Action> {
    @EJB
    ActionDAO actionDAO;
    @Override
    public Action getById(Long id) {
        return actionDAO.getById(id);
    }

    @Override
    public void saveOrUpdate(Action object) {
        actionDAO.saveOrUpdate(object);
    }
    public void saveAll(List<Action> actionList) {
        actionDAO.saveAll(actionList);
    }
    @Override
    public void delete(Action object) {
        actionDAO.delete(object);
    }
}
