package org.my.adventure.dao_manager.ejb.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.my.adventure.dao_manager.api.dao.UserDAO;
import org.my.adventure.dao_manager.api.entities.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Local(UserDAO.class)
public class UserDAOImpl extends CommonDAOImpl<User>{

    public User getUserByName(String userName) {
        Session session = sessionFactory.openSession();
        String hql = "FROM org.my.adventure.dao_manager.api.entities.User as N WHERE logName = :userName";
        Query query = session.createQuery(hql);
        query.setString("userName", userName);
        List<User> result = query.list();
        session.close();
        User user = null;
        if (result != null && result.size() != 0){
            if (result.size() == 1){
                user = result.get(0);
            }
        }
        return user;
    }
}
