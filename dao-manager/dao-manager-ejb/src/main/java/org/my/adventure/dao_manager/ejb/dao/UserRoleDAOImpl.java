package org.my.adventure.dao_manager.ejb.dao;


import org.my.adventure.dao_manager.api.dao.UserRoleDAO;
import org.my.adventure.dao_manager.api.entities.UserRole;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Remote(UserRoleDAO.class)
public class UserRoleDAOImpl extends CommonDAOImpl<UserRole>{
}
