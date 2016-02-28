package org.my.adventure.questgame.impl.nodewr_builder;

import org.my.adventure.dao_manager.api.entities.Action;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.TransitionWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ������ on 28.02.2016.
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

    public void buildImage(List<Action> actions) {
        String typeOfAction = "image";
        Action action = null;
        byte[] image = null;
        if (!actions.isEmpty()) {
            for (Action a : actions) {
                if (a.getType().getName().equals(typeOfAction)) {
                    action = a;
                }
            }
            if (action!=null){
                image = action.getResource().getData();
            }
        }
        nodeWrapper.setImage(image);
    }


}
