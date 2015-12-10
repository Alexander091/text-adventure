package org.textadventure.questeditor.api;

import java.util.Iterator;

/**
 * Created by dimko_000 on 06.12.2015.
 */
public interface Item {
    String getName();
    void setName(String name);
    Iterator<Action> getActions();
    void addAction(Action action);
    void removeAction(Action action);
}
