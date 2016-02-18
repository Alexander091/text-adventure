package org.my.adventure.questeditor.impl.commands;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public abstract class AddCommand<T> implements Command {
    protected T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
