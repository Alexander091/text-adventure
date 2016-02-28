package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questgame.impl.GameStage;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Максим on 17.02.2016.
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

    public Node loadGameByQuestId(long questId){
        Quest quest = questDAO.getById(questId);
        Node startNode = null;
        if(!gameStages.containsKey(questId)){
            startNode = quest.getStartNode();
            gameStages.put(questId, new GameStage(questId,startNode));
        }
        else {
            startNode = gameStages.get(questId).getNode();
        }
        return startNode;
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
