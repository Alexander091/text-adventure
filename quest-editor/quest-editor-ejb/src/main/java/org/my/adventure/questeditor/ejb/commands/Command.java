package org.my.adventure.questeditor.ejb.commands;

import org.my.adventure.questeditor.ejb.beans.GraphEditorBean;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public interface Command {
    void saveToDB(GraphEditorBean graphEditorBean);
    void undo(GraphEditorBean graphEditorBean);
}
