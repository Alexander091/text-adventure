package org.my.adventure.questeditor.ejb.commands;

import org.my.adventure.dao_manager.api.entities.Action;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.questeditor.ejb.beans.GraphEditorBean;
import org.my.adventure.questeditor.ejb.views.NodeView;

import java.util.List;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public class AddNodeViewCommand extends AddCommand<NodeView> {
    public AddNodeViewCommand(NodeView nodeView) {
        this.object = nodeView;
    }
    public void saveToDB(GraphEditorBean graphEditorBean) {
        Node node = object.getEntity();
        List<Action> actionList = node.getActions();
        node.setActions(null);
        graphEditorBean.getNodeBean().saveOrUpdate(object.getEntity());
        for(Action action : actionList)
            action.setNode(node);
        graphEditorBean.getActionBean().saveOrUpdateAll(actionList);
        node.setActions(actionList);
    }

    public void undo(GraphEditorBean graphEditorBean) {
        graphEditorBean.getViewGraph().removeVertex(object);
    }
}
