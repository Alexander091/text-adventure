package org.textadventure.game.impl;

import org.textadventure.questeditor.api.Action;
import org.textadventure.questeditor.api.Condition;
import org.textadventure.questeditor.api.Node;
import org.textadventure.questeditor.api.Transition;

import java.util.Iterator;

/**
 * Created by Alexander on 11.12.2015.
 */
public class GameTransition implements Transition {

    @Override
    public Node getFrom() {
        return null;
    }

    @Override
    public Node getTo() {
        return null;
    }

    @Override
    public void setFrom(Node node) {

    }

    @Override
    public void setTo(Node node) {

    }

    @Override
    public Iterator<Condition> getConditions() {
        return null;
    }

    @Override
    public void addCondition(Condition condition) {

    }

    @Override
    public void removeCondition(Condition condition) {

    }

    @Override
    public Iterator<Action> getActions() {
        return null;
    }

    @Override
    public void addAction(Action action) {

    }

    @Override
    public void removeAction(Action action) {

    }
}
