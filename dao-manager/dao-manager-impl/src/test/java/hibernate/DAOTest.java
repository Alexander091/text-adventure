package hibernate;

import org.junit.Test;
import org.my.adventure.dao_manager.api.entities.UserRole;
import org.my.adventure.dao_manager.impl.dao.UserRoleDAOImpl;
import org.my.adventure.dao_manager.impl.entities.UserRoleEntity;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public class DAOTest {
    @Test
    public void testDAOClasses(){
        UserRoleEntity role = new UserRoleEntity();
        role.setName("test_role");
        UserRoleDAOImpl userRoleDAO = new UserRoleDAOImpl();
        userRoleDAO.saveOrUpdate(role);
    }
}
