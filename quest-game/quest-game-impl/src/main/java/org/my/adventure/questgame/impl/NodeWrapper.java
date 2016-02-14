package org.my.adventure.questgame.impl;


import java.util.List;

/**
 * Created by Максим on 14.02.2016.
 */
public class NodeWrapper {
    private String name;
    private List<TransitionWrapper> transitions;
    public NodeWrapper(String name, List<TransitionWrapper> transitions){
        this.name = name;
        this.transitions = transitions;
    }

    public String getName() {
        return name;
    }

    public List<TransitionWrapper> getTransitions() {
        return transitions;
    }
}
