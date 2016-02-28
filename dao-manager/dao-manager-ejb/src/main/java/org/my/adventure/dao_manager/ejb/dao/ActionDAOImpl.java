package org.my.adventure.dao_manager.ejb.dao;

import org.my.adventure.dao_manager.api.dao.ActionDAO;
import org.my.adventure.dao_manager.api.entities.Action;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Local(ActionDAO.class)
public class ActionDAOImpl extends CommonDAOImpl<Action>{
}
