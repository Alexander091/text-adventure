package org.my.adventure.questeditor.ejb.commands;

import org.my.adventure.questeditor.ejb.beans.GraphEditorBean;
import org.my.adventure.questeditor.ejb.views.TransitionView;

/**
 * Created by dimko_000 on 21.02.2016.
 */
public class DeleteTransitionViewCommand extends DeleteCommand<TransitionView> {
    public DeleteTransitionViewCommand(TransitionView transitionView) {
        object = transitionView;
    }

    public void saveToDB(GraphEditorBean graphEditorBean) {
        graphEditorBean.getTransitionBean().delete(object.getEntity());
    }

    public void undo(GraphEditorBean graphEditorBean) {
        graphEditorBean.getViewGraph().addEdge(object.getFrom(),object.getTo(), object);
    }
}
