package org.my.adventure.queststorage.impl;

import org.hibernate.HibernateError;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Comparator;
import java.util.List;

/**
 * Created by al on 18.02.2016.
 */

@Stateless
public class QuestBean {

    @EJB
    QuestDAO questDAO;

    public List<Quest> getQuests(){
        List<Quest> quests = questDAO.getAll();
        quests.sort(new Comparator<Quest>() {
            public int compare(Quest o1, Quest o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return quests;
    }

    public void deleteQuest(Long id){
        System.out.println("deleting " + id+ " " + questDAO.getById(id));
        try {
            questDAO.delete(questDAO.getById(id));
        }catch (HibernateError h){
            System.out.println(h.getMessage());
        }
    }
}
