package org.my.adventure.dao_manager.api.dao;

import org.my.adventure.dao_manager.api.entities.User;

import javax.ejb.Local;

/**
 * Created by Дмитрий on 11.12.2015.
 */

@Local
public interface UserDAO extends CommonDAO<User> {
}
