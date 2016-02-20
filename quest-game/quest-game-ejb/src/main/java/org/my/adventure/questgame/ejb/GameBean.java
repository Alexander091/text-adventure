package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.QuestWrapper;
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

    @EJB
    GameStagesBean gameStagesBean;

    public NodeWrapper getNextWrappedNode(long questId,long transId) {
        Node node = findTransById(questId,transId).getNodeByToNode();
        gameStagesBean.setNewGameStage(questId, node);
        return getCurrentWrappedNode(questId);
    }

    private Transition findTransById(long questId,long transId){
        Transition tr = null;
        for(Transition t : gameStagesBean.getCurrentTransitionsByQuestId(questId))
            if (t.getId() == transId) {
                tr = t;
                break;
            }
        return tr;
    }

    public List<TransitionWrapper> getWrappedTransitions(List<Transition> transitions) {
        List<TransitionWrapper> trans = new ArrayList<TransitionWrapper>();
        for(Transition tr : transitions)
            trans.add(new TransitionWrapper(tr.getName(),tr.getId()));
        return trans;
    }

    public NodeWrapper getCurrentWrappedNode(long questId){
        Node node = gameStagesBean.getNodeByQuestId(questId);
        return new NodeWrapper(node.getName(),
                node.getDescription(),
                getWrappedTransitions(node.getTransitions()));
    }

    public void loadGame(long questId){
        gameStagesBean.loadGameByQuestId(questId);
    }

    public QuestWrapper getWrappedQuest(long questId){
        Quest quest = gameStagesBean.getQuest(questId);
        return new QuestWrapper(questId, quest.getName());
    }
}