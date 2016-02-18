package org.my.adventure.questeditor.impl.beans;

import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.questeditor.impl.builders.GraphBuilder;
import org.my.adventure.questeditor.impl.builders.QuestBuilder;
import org.my.adventure.questeditor.impl.builders.ViewBuilder;
import org.my.adventure.questeditor.impl.commands.AddNodeViewCommand;
import org.my.adventure.questeditor.impl.commands.Command;
import org.my.adventure.questeditor.impl.views.NodeView;
import org.my.adventure.questeditor.impl.views.TransitionView;
import org.primefaces.json.JSONArray;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by dimko_000 on 18.02.2016.
 */
@Stateful
@LocalBean
public class GraphEditorBean implements Serializable {
    @EJB
    private QuestBean questBean;
    @EJB
    private NodeBean nodeBean;
    private Quest quest = null;
    private Graph<NodeView, TransitionView> viewGraph;
    private List<Command> commandList;

    public Graph<NodeView, TransitionView> getViewGraph() {
        return viewGraph;
    }

    public void setViewGraph(Graph<NodeView, TransitionView> viewGraph) {
        this.viewGraph = viewGraph;
    }

    public NodeBean getNodeBean() {
        return nodeBean;
    }

    public void setNodeBean(NodeBean nodeBean) {
        this.nodeBean = nodeBean;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public QuestBean getQuestBean() {
        return questBean;
    }

    public void setQuestBean(QuestBean questBean) {
        this.questBean = questBean;
    }

    public void loadQuest(Long id) {
        if(id==null)
            quest= QuestBuilder.buildDefaultQuest();
        else
            quest = questBean.getById(id);
        viewGraph = GraphBuilder.buildQuestGraph(quest);
        commandList = new ArrayList<Command>();
    }
    public String addNode(String nodeJson) {
        NodeView nodeView = ViewBuilder.buildNodeView(nodeJson, quest);
        commandList.add(new AddNodeViewCommand(nodeView));
        viewGraph.addVertex(nodeView);
        return nodeView.getJsonOfView().toString();
    }
    public void saveNode(NodeView nodeView) {

    }
    public void undo() {
        Command command = commandList.get(commandList.size() - 1);
        command.undo();
        commandList.remove(command);
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
