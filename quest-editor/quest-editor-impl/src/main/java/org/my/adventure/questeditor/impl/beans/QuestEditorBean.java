package org.my.adventure.questeditor.impl.beans;

import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.questeditor.impl.builders.GraphBuilder;
import org.my.adventure.questeditor.impl.builders.QuestBuilder;
import org.my.adventure.questeditor.impl.views.NodeView;
import org.my.adventure.questeditor.impl.views.TransitionView;
import org.primefaces.json.JSONArray;

import javax.ejb.*;
import javax.json.JsonArray;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dimko_000 on 03.02.2016.
 */
@Stateless
public class QuestEditorBean {
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
