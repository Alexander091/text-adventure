package org.my.adventure.dao_manager.ejb.dao;

import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.dao.TypeOfActionDAO;
import org.my.adventure.dao_manager.api.entities.TypeOfAction;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Local(TypeOfActionDAO.class)
public class TypeOfActionDAOImpl extends CommonDAOImpl<TypeOfAction>{
}
