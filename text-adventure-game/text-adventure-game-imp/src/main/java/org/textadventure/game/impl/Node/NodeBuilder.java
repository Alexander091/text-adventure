package org.textadventure.game.impl.Node;

import org.textadventure.game.impl.Actions.GameAction;
import org.textadventure.game.impl.GameTransition;

import java.util.ArrayList;

/**
 * Created by Alexander on 11.12.2015.
 */
public class NodeBuilder {
    GameNode node;

    public GameNode getNode() {
        return node;
    }

    public void setNode(GameNode node) {
        this.node = node;
    }

    public GameNode buildNode(String text, ArrayList<GameTransition> trns, ArrayList<GameAction> actns)
    {
        return new GameNode();
    }

    //public GameNode addActions(GameAction[] actns)
}
