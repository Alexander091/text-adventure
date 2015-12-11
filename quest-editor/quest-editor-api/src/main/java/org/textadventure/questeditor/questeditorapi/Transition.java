package org.textadventure.questeditor.questeditorapi;

import java.util.Iterator;

/**
 * Created by dimko_000 on 06.12.2015.
 */
public interface Transition {
    Node getFrom();
    Node getTo();
    void setFrom(Node node);
    void setTo(Node node);
    Iterator<Condition> getConditions();
    void addCondition(Condition condition);
    void removeCondition(Condition condition);
    Iterator<Action> getActions();
    void addAction(Action action);
    void removeAction(Action action);
}
