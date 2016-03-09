package org.my.adventure.questeditor.ejb.beans;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.questeditor.ejb.builders.QuestBuilder;

import javax.ejb.*;
import java.util.List;

/**
 * Created by dimko_000 on 03.02.2016.
 */
@Stateless
public class QuestEditorBean extends CommonBean<Quest>{
    @EJB
    QuestDAO questDAO;
    @EJB
    NodeDAO nodeDAO;

    public List<Node> getAllNodes(Long questId) {
        List<Node> nodes = nodeDAO.getNodesByQuestId(questId);
        return nodes;
    }

    public Quest getById(Long questId) {
        if(questId==null)
            return QuestBuilder.buildDefaultQuest();
        else
            return questDAO.getById(questId);
    }

    public void saveOrUpdate(Quest quest) {
        questDAO.saveOrUpdate(quest);
    }

    public void delete(Quest quest) {
        questDAO.delete(quest);
    }
}
