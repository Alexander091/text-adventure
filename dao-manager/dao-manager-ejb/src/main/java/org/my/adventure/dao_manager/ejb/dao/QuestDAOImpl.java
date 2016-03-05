package org.my.adventure.dao_manager.ejb.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Local(QuestDAO.class)
public class QuestDAOImpl extends CommonDAOImpl<Quest> implements QuestDAO{

    private final Logger log = LogManager.getLogger(QuestDAOImpl.class);

    public Quest getTestObject() {
        Quest quest = new Quest();
        quest.setDescription("112313");
        return quest;
    }

    public List<Quest> getAllByGenre(String genre){
        Session session = sessionFactory.openSession();
        String hql = "FROM org.my.adventure.dao_manager.api.entities.Quest as N WHERE genre = :genre";
        Query query = session.createQuery(hql);
        query.setString("genre", genre);
        List<Quest> result = query.list();
        session.close();
        return result;
    }

    public void deleteById(Long questId) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = "delete from org.my.adventure.dao_manager.api.entities.Quest where id= :id";
            Query query = session.createQuery(hql);
            log.debug("deleting quest with id = " + questId);
            query.setLong("id", questId);
            int result = query.executeUpdate();
            log.debug("result = " + result);
            if (result > 0 )
                log.debug("quest with id = " + questId + " was removed");
            transaction.commit();
            session.close();
        } catch (Throwable t) {
            transaction.rollback();
            log.error(t.getMessage(), t);
            session.close();
        }
    }
}
