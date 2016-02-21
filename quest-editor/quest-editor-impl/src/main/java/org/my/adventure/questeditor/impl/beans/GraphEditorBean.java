package org.my.adventure.questeditor.impl.beans;

import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.impl.GraphUtils;
import org.my.adventure.questeditor.impl.builders.GraphBuilder;
import org.my.adventure.questeditor.impl.builders.QuestBuilder;
import org.my.adventure.questeditor.impl.builders.ViewBuilder;
import org.my.adventure.questeditor.impl.commands.*;
import org.my.adventure.questeditor.impl.views.NodeView;
import org.my.adventure.questeditor.impl.views.TransitionView;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    @EJB
    private TransitionBean transitionBean;
    private Quest quest = null;
    private Graph<NodeView, TransitionView> viewGraph;
    private List<Command> commandList;

    public TransitionBean getTransitionBean() {
        return transitionBean;
    }

    public void setTransitionBean(TransitionBean transitionBean) {
        this.transitionBean = transitionBean;
    }

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

    public Set<NodeView> getAllNodes() {
        return viewGraph.vertexSet();
    }

    public NodeView addNode(String nodeJson) {
        NodeView nodeView = ViewBuilder.buildNodeView(nodeJson, quest);
        commandList.add(new AddNodeViewCommand(nodeView));
        viewGraph.addVertex(nodeView);
        return nodeView;
    }
    public TransitionView addTransition(String transitionJson) {
        TransitionView transitionView = ViewBuilder.buildTransitionView(transitionJson, viewGraph);
        commandList.add(new AddTransitionViewCommand(transitionView));
        viewGraph.addEdge(transitionView.getFrom(), transitionView.getTo(), transitionView);
        return transitionView;
    }
    public NodeView editNode(String nodeData, String viewId) {
        NodeView nodeView = GraphUtils.getNodeViewByViewId(viewGraph,viewId);
        String oldName = nodeView.getEntity().getName();
        String oldDescription = nodeView.getEntity().getDescription();
        String oldPosition = nodeView.getEntity().getPosition();
        JSONObject nodeJson = new JSONObject(nodeData);
        nodeView.getEntity().setName(nodeJson.getString("name"));
        nodeView.getEntity().setDescription(nodeJson.getString("description"));
        nodeView.getEntity().setPosition(nodeJson.getString("position"));
        commandList.add(new EditNodeViewCommand(nodeView, oldName, oldDescription, oldPosition));
        return nodeView;
    }
    public TransitionView editTransition(String transitionData, String viewId) {
        TransitionView oldTransitionView = GraphUtils.getTransitionViewByViewId(viewGraph,viewId);
        TransitionView newTransitionView = ViewBuilder.buildTransitionView(transitionData, viewGraph);
        commandList.add(new EditTransitionViewCommand(oldTransitionView, newTransitionView));
        viewGraph.removeEdge(oldTransitionView);
        viewGraph.addEdge(newTransitionView.getFrom(), newTransitionView.getTo(), newTransitionView);
        return newTransitionView;
    }
    public NodeView deleteNode(String viewId) {
        NodeView nodeView = GraphUtils.getNodeViewByViewId(viewGraph, viewId);
        Set<TransitionView> edgesOfNode = viewGraph.edgesOf(nodeView);
        commandList.add(new DeleteNodeViewCommand(nodeView, edgesOfNode));
        viewGraph.removeAllEdges(edgesOfNode);
        viewGraph.removeVertex(nodeView);
        return nodeView;
    }
    public TransitionView deleteTransition(String viewId) {
        TransitionView transitionView = GraphUtils.getTransitionViewByViewId(viewGraph, viewId);
        commandList.add(new DeleteTransitionViewCommand(transitionView));
        viewGraph.removeEdge(transitionView);
        return transitionView;
    }
    public String save() {
        for(Command command : commandList)
            command.saveToDB(this);
        commandList.clear();
        return successResponse();
    }
    public String undo() {
        Command command = commandList.get(commandList.size() - 1);
        command.undo(this);
        commandList.remove(command);
        return successResponse();
    }
    public String successResponse() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("response", "success");
        return jsonObject.toString();
    }
}
