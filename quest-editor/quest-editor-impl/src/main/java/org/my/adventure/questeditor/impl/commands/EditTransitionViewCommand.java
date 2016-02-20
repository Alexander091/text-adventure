package org.my.adventure.questeditor.impl.commands;

import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.impl.beans.GraphEditorBean;
import org.my.adventure.questeditor.impl.views.TransitionView;

/**
 * Created by dimko_000 on 20.02.2016.
 */
public class EditTransitionViewCommand extends EditCommand<TransitionView> {
    private TransitionView newObject;
    public EditTransitionViewCommand(TransitionView oldObject, TransitionView newObject) {
        this.object = oldObject;
        this.newObject = newObject;
    }

    public void saveToDB(GraphEditorBean graphEditorBean) {
        graphEditorBean.getTransitionBean().delete(object.getEntity());
        Transition transition = newObject.getEntity();
        transition.setNodeByFromNode(newObject.getFrom().getEntity());
        transition.setNodeByToNode(newObject.getTo().getEntity());
        graphEditorBean.getTransitionBean().saveOrUpdate(transition);
    }

    public void undo(GraphEditorBean graphEditorBean) {
        graphEditorBean.getViewGraph().removeEdge(newObject);
        graphEditorBean.getViewGraph().addEdge(newObject.getFrom(),newObject.getTo(),newObject);
    }
}
