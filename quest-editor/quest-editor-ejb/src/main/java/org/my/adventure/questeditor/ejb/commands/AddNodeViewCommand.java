package org.my.adventure.questeditor.ejb.commands;

import org.my.adventure.questeditor.ejb.beans.GraphEditorBean;
import org.my.adventure.questeditor.ejb.views.NodeView;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public class AddNodeViewCommand extends AddCommand<NodeView> {
    public AddNodeViewCommand(NodeView nodeView) {
        this.object = nodeView;
    }
    public void saveToDB(GraphEditorBean graphEditorBean) {
        graphEditorBean.getNodeBean().saveOrUpdate(object.getEntity());
    }

    public void undo(GraphEditorBean graphEditorBean) {
        graphEditorBean.getViewGraph().removeVertex(object);
    }
}
