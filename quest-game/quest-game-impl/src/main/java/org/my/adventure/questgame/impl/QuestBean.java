package org.my.adventure.questgame.impl;

import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;

/**
 * Created by Максим on 09.02.2016.
 */


@Stateful
public class QuestBean {
    private static long currentQuestId;
    @EJB
    QuestDAO questDAO;

    //!!!???
    static Quest quest = null;

    public void loadQuest(long id) {
        quest = questDAO.getById(id);
        currentQuestId = id;

    }
    public String getName() {
        return quest.getName();
    }
    public int getVersion() {
        return quest.getVersion();
    }
    public int getAgeLimit() {
        return quest.getAgeLimit();
    }
    public String getQuestDescription(){return quest.getDescription();}

    public static long getCurrentQuestId() {
        return currentQuestId;
    }
}
