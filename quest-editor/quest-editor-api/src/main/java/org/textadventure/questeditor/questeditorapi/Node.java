package org.textadventure.questeditor.questeditorapi;

import java.util.Iterator;

/**
 * Created by dimko_000 on 06.12.2015.
 */
public interface Node {
    void setName(String name);
    String getName();
    Iterator<Transition> getTransitions();
    void addTransition(Transition transition);
    void removeTransition(Transition transition);
    Iterator<Action> getActions();
    void addAction(Action action);
    void removeAction(Action action);
}
