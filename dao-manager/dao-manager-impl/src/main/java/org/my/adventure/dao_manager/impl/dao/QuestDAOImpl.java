package org.my.adventure.dao_manager.impl.dao;

import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Common;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Named
@SessionScoped
public class QuestDAOImpl extends CommonDAOImpl<Quest> implements QuestDAO{

    @Override
    public Quest getTestObject() {
        Quest quest = new Quest();
        quest.setDescription("112313");
        return quest;
    }
}
