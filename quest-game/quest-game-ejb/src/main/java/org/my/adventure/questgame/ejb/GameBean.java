package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.entities.*;
import org.my.adventure.questgame.impl.nodewr_builder.NodeWrapperDirector;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.QuestWrapper;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

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
        return new NodeWrapperDirector().constructNodeWrapper(node);
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

    public NodeWrapper loadGame(long questId){
        return new NodeWrapperDirector().constructNodeWrapper(gameStagesBean.loadGameByQuestId(questId));
    }

    public QuestWrapper getWrappedQuest(long questId){
        Quest quest = gameStagesBean.getQuest(questId);
        return new QuestWrapper(questId, quest.getName());
    }

    public void refresh(long questId) {
        gameStagesBean.refresh(questId);
    }
}