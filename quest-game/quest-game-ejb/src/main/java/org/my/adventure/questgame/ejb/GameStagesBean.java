package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questgame.impl.GameStage;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.QuestWrapper;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ������ on 17.02.2016.
 */

@Stateful
public class GameStagesBean {
    private Map<Long,GameStage> gameStages;
    @EJB
    QuestDAO questDAO;
    @EJB
    NodeDAO nodeDAO;

    @PostConstruct
    void init(){
        gameStages = new HashMap<Long, GameStage>();
    }

    public Map<Long,GameStage> getGameStages() {
        return gameStages;
    }

    public void loadGameByQuestId(long questId){
        Quest quest = questDAO.getById(questId);
        if(!gameStages.containsKey(questId)){
            gameStages.put(questId, new GameStage(questId,quest.getStartNode()));
        }
    }

    public List<Transition> getCurrentTransitionsByQuestId(long questId){
        return gameStages.get(questId).getNodeTransitions();
    }

    public Node getNodeByQuestId(long questId){
        return gameStages.get(questId).getNode();
    }

    public Quest getQuest(long questId){
        return questDAO.getById(questId);
    }

    public void setNewGameStage(long questId, Node node){
        gameStages.put(questId, new GameStage(questId, node));
    }
}