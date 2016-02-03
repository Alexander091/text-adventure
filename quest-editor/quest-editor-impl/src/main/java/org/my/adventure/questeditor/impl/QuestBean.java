package org.my.adventure.questeditor.impl;

import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Created by dimko_000 on 03.02.2016.
 */
@Stateful
public class QuestBean {
    @EJB
    QuestDAO questDAO;
    Quest quest = null;
    public void loadQuest(long id) {
       quest = questDAO.getById(id);
    }
    public String getQuestData() {
        return "[{\"group\": \"nodes\", \"data\": {\"id\": \"1\"}, \"position\": { \"x\": 100, \"y\": 100}}]";
    }
    public String getName() {
        return quest.getName();
    }
    public String getDescription(){
        return quest.getDescription();
    }
    public String getGenre() {
        return quest.getGenre();
    }
    public int getVersion() {
        return quest.getVersion();
    }
    public int getAgeLimit() {
        return quest.getAgeLimit();
    }
}
