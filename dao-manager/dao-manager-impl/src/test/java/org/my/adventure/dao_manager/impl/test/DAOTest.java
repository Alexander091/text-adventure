package org.my.adventure.dao_manager.impl.test;

import org.junit.Test;
import org.my.adventure.dao_manager.api.dao.UserRoleDAO;
import org.my.adventure.dao_manager.api.entities.UserRole;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public class DAOTest {
    //@Test
    public void testDAOClasses(){
        UserRole role = new UserRole();
        role.setName("test_role");
       /* UserRoleDAO userRoleDAO = new UserRoleDAO();
        userRoleDAO.saveOrUpdate(role);
        UserRole userRoleFromDB = userRoleDAO.getById(role.getId());
        userRoleDAO.delete(role);*/
    }
}
