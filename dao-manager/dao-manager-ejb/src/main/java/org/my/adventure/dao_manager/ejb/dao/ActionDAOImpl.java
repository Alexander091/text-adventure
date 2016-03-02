package org.my.adventure.dao_manager.ejb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.my.adventure.dao_manager.api.dao.ActionDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Action;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Local(ActionDAO.class)
public class ActionDAOImpl extends CommonDAOImpl<Action> implements ActionDAO {
    public void saveAll(List<Action> actionList) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for(int i = 0; i<actionList.size(); i++) {
            session.save(actionList.get(i));
            if(i%50==0) {
                session.flush();
                session.clear();
            }
        }
        transaction.commit();
        session.close();
    }
}
