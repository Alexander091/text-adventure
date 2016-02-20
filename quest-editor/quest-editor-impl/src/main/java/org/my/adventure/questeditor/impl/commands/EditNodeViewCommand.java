package org.my.adventure.questeditor.impl.commands;

import org.my.adventure.questeditor.impl.beans.GraphEditorBean;
import org.my.adventure.questeditor.impl.views.NodeView;

/**
 * Created by dimko_000 on 20.02.2016.
 */
public class EditNodeViewCommand  extends EditCommand<NodeView>{
    private String oldName;
    private String oldDescription;
    private String oldPosition;
    public EditNodeViewCommand(NodeView object, String oldName, String oldDescription, String oldPosition) {
        this.object = object;
        this.oldName = oldName;
        this.oldDescription = oldDescription;
        this.oldPosition = oldPosition;
    }

    public void saveToDB(GraphEditorBean graphEditorBean) {
        graphEditorBean.getNodeBean().saveOrUpdate(object.getEntity());
    }

    public void undo(GraphEditorBean graphEditorBean) {
        object.getEntity().setName(oldName);
        object.getEntity().setDescription(oldDescription);
        object.getEntity().setPosition(oldPosition);
    }
}
