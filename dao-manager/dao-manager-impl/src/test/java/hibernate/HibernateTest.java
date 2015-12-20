package hibernate;

import org.my.adventure.dao_manager.impl.configurations.HibernateUtil;
import org.my.adventure.dao_manager.impl.entities.UserEntity;
import org.my.adventure.dao_manager.impl.entities.UserRoleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public class HibernateTest {

    /*private static SessionFactory sessionFactory;
    private Session session;

    @BeforeClass
    public static void getSessionFactory(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Before
    public void getSession(){
        session = sessionFactory.openSession();
    }

    @Test
    public void hibernateConnectionToUserRoleTableTest(){
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setName("test_role");
        session.save(userRoleEntity);
        session.flush();
        UserRoleEntity userRoleFromDB = (UserRoleEntity) session.get(UserRoleEntity.class, userRoleEntity.getId());
        Assert.assertEquals(userRoleEntity,userRoleFromDB);
        session.delete(userRoleFromDB);
        session.flush();
    }

    @Test
    public void hibernateConnectionToUserTableTest(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test_user");
        userEntity.setPassword("1234");
        userEntity.setEmail("kgodgdg");
        userEntity.setLogName("log");
        session.save(userEntity);
        session.flush();
        UserEntity userFromDB = (UserEntity) session.get(UserEntity.class, userEntity.getId());
        Assert.assertEquals(userEntity,userFromDB);
        session.delete(userFromDB);
        session.flush();
    }

    @AfterClass
    public static void closeSessionFactory(){
        sessionFactory.close();
    }

    @After
    public void closeSession(){
        session.close();
    }*/
}
