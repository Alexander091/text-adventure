package org.my.adventure.questeditor.impl.commands;

import org.jgrapht.Graph;
import org.my.adventure.questeditor.impl.beans.GraphEditorBean;
import org.my.adventure.questeditor.impl.views.NodeView;
import org.my.adventure.questeditor.impl.views.TransitionView;

import javax.ejb.EJB;

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
