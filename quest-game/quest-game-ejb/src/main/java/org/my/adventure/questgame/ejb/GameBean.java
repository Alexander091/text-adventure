package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.TransitionWrapper;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 28.01.2016.
 */


@Stateful
public class GameBean {
    //private Node currentNode;
    //private List<Transition> transitions;

    @EJB
    CurrentGameStageBean currentGameStageBean;

    /*@PostConstruct
    void init(){
        currentNode = currentGameStageBean.getNode();
        transitions = currentGameStageBean.getNode().getTransitions();
    }*/

    public NodeWrapper getNextWrappedNode(long transId) {
        //currentNode = findTransById(transId).getNodeByToNode();
        Node node = findTransById(transId).getNodeByToNode();
        currentGameStageBean.setNode(node);
        //transitions = currentGameStageBean.getNode().getTransitions();
        return getCurrentWrappedNode();
    }

    private Transition findTransById(long id){
        Transition tr = null;
        for(Transition t : currentGameStageBean.getNode().getTransitions())
            if (t.getId() == id) {
                tr = t;
                break;
            }
        return tr;
    }

    public List<TransitionWrapper> getWrappedTransitions() {
        List<TransitionWrapper> trans = new ArrayList<TransitionWrapper>();
        for(Transition tr : currentGameStageBean.getNode().getTransitions())
            trans.add(new TransitionWrapper(tr.getName(),tr.getId()));
        return trans;
    }

    public NodeWrapper getCurrentWrappedNode(){
        return new NodeWrapper(currentGameStageBean.getNode().getName(),currentGameStageBean.getNode().getDescription(),getWrappedTransitions());
    }

    public void loadGame(long questId){
        currentGameStageBean.loadGameByQuestId(questId);
    }

    public String getQuestName(){
        return currentGameStageBean.getWrappedQuest().getQuestName();
    }
}