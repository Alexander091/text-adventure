package hibernate;

import org.junit.Test;
import org.my.adventure.dao_manager.api.entities.UserRole;
import org.my.adventure.dao_manager.impl.dao.UserRoleDAOImpl;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public class DAOTest {
    //@Test
    public void testDAOClasses(){
        UserRole role = new UserRole();
        role.setName("test_role");
        UserRoleDAOImpl userRoleDAO = new UserRoleDAOImpl();
        userRoleDAO.saveOrUpdate(role);
        UserRole userRoleFromDB = userRoleDAO.getById(role.getId());
        userRoleDAO.delete(role);
    }
}
