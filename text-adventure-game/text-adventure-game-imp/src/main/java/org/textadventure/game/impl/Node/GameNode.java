package org.textadventure.game.impl.Node;
import org.textadventure.questeditor.api.Action;
import org.textadventure.questeditor.api.Node;
import org.textadventure.questeditor.api.Transition;

import java.util.Iterator;

/**
 * Created by Alexander on 11.12.2015.
 */
public class GameNode implements Node {
    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Iterator<Transition> getTransitions() {
        return null;
    }

    @Override
    public void addTransition(Transition transition) {

    }

    @Override
    public void removeTransition(Transition transition) {

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
