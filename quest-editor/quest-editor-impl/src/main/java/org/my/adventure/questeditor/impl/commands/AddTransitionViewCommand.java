package org.my.adventure.questeditor.impl.commands;

import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.impl.beans.GraphEditorBean;
import org.my.adventure.questeditor.impl.views.TransitionView;

/**
 * Created by dimko_000 on 19.02.2016.
 */
public class AddTransitionViewCommand extends AddCommand<TransitionView> {
    public AddTransitionViewCommand(TransitionView transitionView) {
        this.object = transitionView;
    }
    public void saveToDB(GraphEditorBean graphEditorBean) {
        Transition transition = object.getEntity();
        transition.setNodeByFromNode(object.getFrom().getEntity());
        transition.setNodeByToNode(object.getTo().getEntity());
        graphEditorBean.getTransitionBean().saveOrUpdate(transition);
    }

    public void undo(GraphEditorBean graphEditorBean) {
        graphEditorBean.getViewGraph().removeEdge(object);
    }
}
