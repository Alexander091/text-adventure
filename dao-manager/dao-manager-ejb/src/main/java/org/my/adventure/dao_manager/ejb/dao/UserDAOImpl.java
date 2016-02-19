package org.my.adventure.dao_manager.ejb.dao;

import org.my.adventure.dao_manager.api.dao.UserDAO;
import org.my.adventure.dao_manager.api.entities.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Remote(UserDAO.class)
public class UserDAOImpl extends CommonDAOImpl<User>{
}
