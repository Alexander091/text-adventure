package org.my.adventure.questgame.impl.wrappers;


import java.util.List;

/**
 * Created by Максим on 14.02.2016.
 */
public class NodeWrapper {
    private String name = "";
    private String text = "";
    private List<TransitionWrapper> transitions = null;
    private byte[] image = null;
    private byte[] sound = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TransitionWrapper> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<TransitionWrapper> transitions) {
        this.transitions = transitions;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setSound(byte[] sound) {
        this.sound = sound;
    }
    /*public NodeWrapper(String name, String text, List<TransitionWrapper> transitions){
        this.name = name;
        this.text = text;
        this.transitions = transitions;
    }*/



}
