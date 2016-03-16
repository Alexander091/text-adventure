package org.my.adventure.questeditor.ejb.commands;

import org.my.adventure.dao_manager.api.entities.Action;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.questeditor.ejb.beans.GraphEditorBean;
import org.my.adventure.questeditor.ejb.views.NodeView;

import java.util.List;

/**
 * Created by dimko_000 on 20.02.2016.
 */
public class EditNodeViewCommand  extends EditCommand<NodeView>{
    private String oldName;
    private String oldDescription;
    private String oldPosition;
    private List<Action> oldActions;
    public EditNodeViewCommand(NodeView object, String oldName, String oldDescription, String oldPosition, List<Action> oldActions) {
        this.object = object;
        this.oldName = oldName;
        this.oldDescription = oldDescription;
        this.oldPosition = oldPosition;
        this.oldActions = oldActions;
    }

    public void saveToDB(GraphEditorBean graphEditorBean) {
        Node node = object.getEntity();
        List<Action> actionList = node.getActions();
        node.setActions(null);
        graphEditorBean.getNodeBean().saveOrUpdate(node);
        for(Action action : actionList) {
            action.setNode(node);
        }
        graphEditorBean.getActionBean().deleteAll(oldActions);
        graphEditorBean.getActionBean().saveOrUpdateAll(actionList);
        node.setActions(actionList);
    }

    public void undo(GraphEditorBean graphEditorBean) {
        object.getEntity().setName(oldName);
        object.getEntity().setDescription(oldDescription);
        object.getEntity().setPosition(oldPosition);
        object.getEntity().setActions(oldActions);
    }
}
