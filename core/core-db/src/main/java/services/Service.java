package services;

import entities.UserRoleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Service {
    public static void main(String[] args) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setName("user");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.save(userRoleEntity);
        session.close();
        session = HibernateUtil.getSessionFactory().openSession();
        List<UserRoleEntity> getRoles = session.createCriteria(UserRoleEntity.class).list();
        for(UserRoleEntity getRole : getRoles)
            System.out.println(getRole.getId()+"  " + getRole.getName());
        session.close();
    }
}
