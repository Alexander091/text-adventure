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
    @EJB
    GraphEditorBean graphEditorBean;
    public AddNodeViewCommand(NodeView nodeView) {
        this.object = nodeView;
    }
    public void saveToDB() {
        graphEditorBean.getNodeBean().saveOrUpdate(object.getEntity());
    }

    public void undo() {
        graphEditorBean.getViewGraph().removeVertex(object);
    }
}
