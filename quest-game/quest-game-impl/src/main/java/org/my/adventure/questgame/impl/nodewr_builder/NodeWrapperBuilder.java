package org.my.adventure.questgame.impl.nodewr_builder;

import org.my.adventure.dao_manager.api.entities.Action;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.TransitionWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 28.02.2016.
 */
public class NodeWrapperBuilder {
    protected NodeWrapper nodeWrapper;
    public NodeWrapper getNodeWrapper(){
        return nodeWrapper;
    }
    public void createNodeWrapper(){
        nodeWrapper = new NodeWrapper();
    }

    public void buildNodeName(String name) {
        nodeWrapper.setName(name);
    }

    public void buildNodeText(String text) {
        nodeWrapper.setText(text);
    }

    public void buildTransitions(List<Transition> transitions) {
        List<TransitionWrapper> trans = new ArrayList<TransitionWrapper>();
        for(Transition tr : transitions)
            trans.add(new TransitionWrapper(tr.getName(),tr.getId()));
        nodeWrapper.setTransitions(trans);
    }

    public void buildActions(List<Action> actions) {
        long imageResourceId=0;
        long soundResourceId=0;

        /*final long typeOfActionId = 1;
        Action action = null;

        if (!actions.isEmpty()) {
            for (Action a : actions) {
                if (a.getType().getId().equals(typeOfActionId)) {
                    action = a;
                }
            }
            if (action!=null){
                image = action.getResource().getData();
            }
        }*/


        if (!actions.isEmpty()) {
            for (Action act : actions) {
                switch (act.getType().getId().intValue()) {
                    case 1: //image
                        imageResourceId = act.getResource().getId();
                        break;
                    case 2: //sound
                        soundResourceId = act.getResource().getId();
                        break;
                }
            }
        }
        nodeWrapper.setImageResourceId(imageResourceId);
        nodeWrapper.setSoundResourceId(soundResourceId);
    }

}
