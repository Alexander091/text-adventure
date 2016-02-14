package org.my.adventure.questgame.impl;

import javax.ejb.Stateful;

/**
 * Created by Максим on 14.02.2016.
 */

public class TransitionWrapper {
    private String text;
    private long id;
    public TransitionWrapper(String text, long id){
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }
}
