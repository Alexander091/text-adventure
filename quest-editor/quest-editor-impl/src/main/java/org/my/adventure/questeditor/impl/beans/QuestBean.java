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

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.json.JsonArray;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dimko_000 on 03.02.2016.
 */
@Stateful
public class QuestBean implements Serializable{
    @EJB
    QuestDAO questDAO;
    @EJB
    NodeDAO nodeDAO;
    Quest quest = null;
    Graph<NodeView, TransitionView> viewGraph;

    public Quest getQuest() {
        return quest;
    }

    public void loadQuest(Long id) {
        if(id==null)
            quest=QuestBuilder.buildDefaultQuest();
        else
            quest = questDAO.getById(id);
        viewGraph = GraphBuilder.buildQuestGraph(quest);
    }
    public Long saveQuest() {
        return questDAO.saveOrUpdate(quest);
    }
    public List<Node> getAllNodes() {
        List<Node> nodes = nodeDAO.getNodesByQuestId(quest.getId());
        return nodes;
    }
    public String getQuestGraphInJson() {
        JSONArray graphJson = new JSONArray();
        for(NodeView nodeView : viewGraph.vertexSet())
            graphJson.put(nodeView.getJsonOfView());
        for (TransitionView transitionView : viewGraph.edgeSet())
            graphJson.put(transitionView.getJsonOfView());
        return graphJson.toString();
    }
}
