package org.my.adventure.questeditor.impl.commands;

import org.my.adventure.questeditor.impl.beans.GraphEditorBean;

/**
 * Created by dimko_000 on 20.02.2016.
 */
public abstract class EditCommand<T> implements Command {
    protected T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
