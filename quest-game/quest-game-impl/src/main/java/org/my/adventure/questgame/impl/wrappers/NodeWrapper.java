package org.my.adventure.questgame.impl.wrappers;


import java.util.List;

/**
 * Created by Максим on 14.02.2016.
 */
public class NodeWrapper {
    private String name;
    private String text;
    private List<TransitionWrapper> transitions;
    public NodeWrapper(String name, String text, List<TransitionWrapper> transitions){
        this.name = name;
        this.text = text;
        this.transitions = transitions;
    }

    public String getName() {
        return name;
    }

    public List<TransitionWrapper> getTransitions() {
        return transitions;
    }

    public String getText() {
        return text;
    }

}
