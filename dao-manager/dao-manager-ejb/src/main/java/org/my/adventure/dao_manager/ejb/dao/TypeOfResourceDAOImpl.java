package org.my.adventure.dao_manager.ejb.dao;

import org.my.adventure.dao_manager.api.dao.TypeOfResourceDAO;
import org.my.adventure.dao_manager.api.entities.TypeOfResource;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by Дмитрий on 11.12.2015.
 */

@Stateless
@Local(TypeOfResourceDAO.class)
public class TypeOfResourceDAOImpl extends CommonDAOImpl<TypeOfResource>{
}
