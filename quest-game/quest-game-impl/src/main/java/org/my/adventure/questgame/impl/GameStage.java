package org.my.adventure.questgame.impl;

import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Transition;

import java.util.List;

/**
 * Created by Максим on 17.02.2016.
 */

public class GameStage {
    
    private long questId;
    private Node node;

    public GameStage (long questId, Node node){
        this.questId = questId;
        this.node = node;
    }

    public long getQuestId() {
        return questId;
    }

    public List<Transition> getNodeTransitions(){
        return node.getTransitions();
    }

    public void setNode(Node node) {
       this.node = node;
    }

    public Node getNode() {
        return node;
    }
}
