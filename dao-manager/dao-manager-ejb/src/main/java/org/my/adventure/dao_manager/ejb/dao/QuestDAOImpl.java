package org.my.adventure.dao_manager.ejb.dao;

import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
public class QuestDAOImpl implements QuestDAO, Serializable{

    public Quest getTestObject() {
        Quest quest = new Quest();
        quest.setDescription("112313");
        return quest;
    }
}
