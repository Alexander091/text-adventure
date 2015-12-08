package services;

import entities.UserRoleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Service {
    public static void main(String[] args) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setName("super_admin");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.save(userRoleEntity);
        session.flush();
        session.close();
        session = sessionFactory.openSession();
        List<UserRoleEntity> getRoles = session.createCriteria(UserRoleEntity.class).list();
        for(UserRoleEntity getRole : getRoles)
            System.out.println(getRole.getId()+"  " + getRole.getName());
        session.close();
        System.out.println(Long.MAX_VALUE);
        sessionFactory.close();
    }
}
