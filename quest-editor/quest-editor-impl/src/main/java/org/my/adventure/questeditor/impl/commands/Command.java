package org.my.adventure.questeditor.impl.commands;

import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.impl.beans.GraphEditorBean;
import org.my.adventure.questeditor.impl.views.EntityView;
import org.my.adventure.questeditor.impl.views.NodeView;
import org.my.adventure.questeditor.impl.views.TransitionView;

import java.util.List;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public interface Command {
    void saveToDB(GraphEditorBean graphEditorBean);
    void undo(GraphEditorBean graphEditorBean);
}
