package org.my.adventure.questeditor.ejb.commands;

import org.my.adventure.questeditor.ejb.beans.GraphEditorBean;
import org.my.adventure.questeditor.ejb.views.NodeView;
import org.my.adventure.questeditor.ejb.views.TransitionView;

import java.util.Set;

/**
 * Created by dimko_000 on 21.02.2016.
 */
public class DeleteNodeViewCommand extends DeleteCommand<NodeView> {
    Set<TransitionView> transitions;
    public DeleteNodeViewCommand(NodeView nodeView, Set<TransitionView> transitions) {
        object = nodeView;
        this.transitions = transitions;
    }

    public void saveToDB(GraphEditorBean graphEditorBean) {
        for(TransitionView transitionView : transitions)
            graphEditorBean.getTransitionBean().delete(transitionView.getEntity());
        graphEditorBean.getNodeBean().delete(object.getEntity());
    }

    public void undo(GraphEditorBean graphEditorBean) {
        graphEditorBean.getViewGraph().addVertex(object);
        for (TransitionView transitionView : transitions)
            graphEditorBean.getViewGraph().addEdge(transitionView.getFrom(),transitionView.getTo(), transitionView);
    }
}
