package org.my.adventure.queststorage.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateError;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by al on 18.02.2016.
 */

@Stateless
public class QuestStorageBean {

    private final Logger log = LogManager.getLogger(QuestStorageBean.class);

    @EJB
    QuestDAO questDAO;

    public List<QuestWrapper> getQuests(){
        List<Quest> quests = questDAO.getAll();
        List<QuestWrapper> questWrappers = new ArrayList<>();
        for (Quest quest : quests) {
            questWrappers.add(new QuestWrapper(quest.getId(), quest.getDescription(), quest.getGenre(), quest.getVersion(),
                    quest.getAgeLimit(), quest.getRating(), quest.getName(), quest.getImage().getPath()));
        }
        questWrappers.sort(new Comparator<QuestWrapper>() {
            public int compare(QuestWrapper o1, QuestWrapper o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return questWrappers;
    }

    public void deleteQuest(Long id){
        log.debug("deleting " + id+ " " + questDAO.getById(id));
        try {
            questDAO.deleteById(id);
        }catch (HibernateError h){
            log.error(h.getMessage());
        }
    }
}
