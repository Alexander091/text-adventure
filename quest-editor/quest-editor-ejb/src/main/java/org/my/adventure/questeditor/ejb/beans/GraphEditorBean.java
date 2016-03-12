package org.my.adventure.questeditor.ejb.beans;

import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.dao.TypeOfActionDAO;
import org.my.adventure.dao_manager.api.entities.Action;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Resource;
import org.my.adventure.dao_manager.api.entities.TypeOfAction;
import org.my.adventure.questeditor.ejb.GraphUtils;
import org.my.adventure.questeditor.ejb.builders.ActionBuilder;
import org.my.adventure.questeditor.ejb.builders.GraphBuilder;
import org.my.adventure.questeditor.ejb.builders.QuestBuilder;
import org.my.adventure.questeditor.ejb.builders.ViewBuilder;
import org.my.adventure.questeditor.ejb.commands.*;
import org.my.adventure.questeditor.ejb.graph.vaildator.ValidationStatus;
import org.my.adventure.questeditor.ejb.graph.vaildator.Validator;
import org.my.adventure.questeditor.ejb.views.NodeView;
import org.my.adventure.questeditor.ejb.views.TransitionView;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dimko_000 on 18.02.2016.
 */
@Stateful
@LocalBean
public class GraphEditorBean implements Serializable {
    @EJB
    private QuestEditorBean questEditorBean;
    @EJB
    private NodeBean nodeBean;
    @EJB
    private TransitionBean transitionBean;
    @EJB
    private TypeOfActionBean typeOfActionBean;
    @EJB
    private ResourceEditorBean resourceEditorBean;
    @EJB
    private ActionBean actionBean;

    private Quest quest = null;
    private Graph<NodeView, TransitionView> viewGraph;
    private List<Command> commandList;
    private Validator validator;

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

    public QuestEditorBean getQuestEditorBean() {
        return questEditorBean;
    }

    public void setQuestEditorBean(QuestEditorBean questEditorBean) {
        this.questEditorBean = questEditorBean;
    }

    public ActionBean getActionBean() {
        return actionBean;
    }

    public void loadQuest(Long id) {
        if(id==null)
            quest= QuestBuilder.buildDefaultQuest();
        else
            quest = questEditorBean.getById(id);
        viewGraph = GraphBuilder.buildQuestGraph(quest);
        commandList = new ArrayList<Command>();
        validator = new Validator();
    }

    public Set<NodeView> getAllNodes() {
        return viewGraph.vertexSet();
    }

    public NodeView addNode(String nodeJson) {
        NodeView nodeView = ViewBuilder.buildNodeView(nodeJson, quest);
        List<Action> actions = ActionBuilder.buildActions(nodeJson);
        nodeView.getEntity().setActions(actions);
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
        List<Action> oldActions = nodeView.getEntity().getActions();
        JSONObject nodeJson = new JSONObject(nodeData);
        nodeView.getEntity().setName(nodeJson.getString("name"));
        nodeView.getEntity().setDescription(nodeJson.getString("description"));
        nodeView.getEntity().setPosition(nodeJson.getString("position"));
        nodeView.getEntity().setActions(ActionBuilder.buildActions(nodeData));
        commandList.add(new EditNodeViewCommand(nodeView, oldName, oldDescription, oldPosition, oldActions));
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
        if(nodeView.getEntity().getId()!=null && nodeView.getEntity().getId().longValue() == quest.getStartNode().getId().longValue())
            return null;
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
    public List<TypeOfAction> getAllTypesOfAction() {
        return typeOfActionBean.getAllTypes();
    }
    public List<Resource> getResourcesList(Long questId, Long typeOfActionId) {
        TypeOfAction typeOfAction = typeOfActionBean.getById(typeOfActionId);
        return resourceEditorBean.getResourcesList(questId, typeOfAction.getTypeOfResource().getId());
    }
    public String save(JSONArray data) {
        ValidationStatus connectivityStatus = validator.testConnectivity(viewGraph);
        ValidationStatus startEndStatus = validator.testStartEnd(viewGraph);
        if(connectivityStatus.equals(ValidationStatus.VALID) && startEndStatus.equals(ValidationStatus.VALID)) {
            for (Command command : commandList)
                command.saveToDB(this);
            commandList.clear();
            updatePositions(data);
        }
        return buildResponse(connectivityStatus, startEndStatus);
    }
    public String undo() {
        Command command = commandList.get(commandList.size() - 1);
        command.undo(this);
        commandList.remove(command);
        return successResponse();
    }
    public void updatePositions(JSONArray data) {
        for(int i = 0; i<data.length(); i++) {
            JSONObject nodeJson = data.getJSONObject(i);
            NodeView nodeView = GraphUtils.getNodeViewByViewId(getViewGraph(), nodeJson.getString("id"));
            nodeView.getEntity().setPosition(nodeJson.getString("position"));
            nodeBean.saveOrUpdate(nodeView.getEntity());
        }
    }
    public String buildResponse(ValidationStatus connectivityStatus,ValidationStatus startEndStatus) {
        JSONObject jsonObject = new JSONObject();
        JSONArray responseArray = new JSONArray();
        if(connectivityStatus.equals(ValidationStatus.VALID) && startEndStatus.equals(ValidationStatus.VALID)) {
            responseArray.put("success");
            jsonObject.put("response", responseArray);
        }
        else {
            switch (connectivityStatus) {
                case VALID:
                    break;
                case NOT_VALID_CONNECTIVITY:
                    responseArray.put("connectivity_error");
                    break;
            }
            switch (startEndStatus) {
                case VALID:
                    break;
                case NOT_VALID_START_NODE:
                    responseArray.put("invalid_start"); break;
                case NOT_VALID_END_NODE:
                    responseArray.put("invalid_end"); break;
                case NOT_VALID_START_AND_END_NODE:
                    responseArray.put("invalid_start_and_end"); break;
            }
            jsonObject.put("response", responseArray);
        }
        return jsonObject.toString();
    }
    public String successResponse() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("response", "success");
        return jsonObject.toString();
    }
    public String errorResponse() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("response", "error");
        return jsonObject.toString();
    }
}
