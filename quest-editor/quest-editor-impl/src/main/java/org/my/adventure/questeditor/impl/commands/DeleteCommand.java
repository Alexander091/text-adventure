package org.my.adventure.questeditor.impl.commands;

/**
 * Created by dimko_000 on 21.02.2016.
 */
public abstract class DeleteCommand<T> implements Command {
    protected T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
