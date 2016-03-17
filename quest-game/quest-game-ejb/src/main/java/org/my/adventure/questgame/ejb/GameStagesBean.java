package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.entities.*;
import org.my.adventure.questgame.impl.GameStage;
import org.my.adventure.questgame.impl.QuestTimer;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.*;

/**
 * Created by Максим on 17.02.2016.
 */

@Stateful
public class GameStagesBean {
    private Map<Long,Stack<GameStage>> gameStagesStacks;// = new HashMap<Long, Stack<GameStage>>();
    private Map<Long, QuestTimer> timeMap;// = new HashMap<Long, QuestTimer>();

    @EJB
    QuestDAO questDAO;
    @EJB
    NodeDAO nodeDAO;
    @EJB
    ResourceDAO resourceDAO;

    @PostConstruct
    void init(){
        gameStagesStacks = new HashMap<Long, Stack<GameStage>>();
        timeMap = new HashMap<Long, QuestTimer>();
    }


    public Map<Long,Stack<GameStage>> getGameStages() {
        return gameStagesStacks;
    }

    public Node loadGameByQuestId(long questId){
        Quest quest = questDAO.getById(questId);
        Node startNode = null;
        if(!gameStagesStacks.containsKey(questId)){
            startNode = quest.getStartNode();
            gameStagesStacks.put(questId, new Stack<GameStage>());
            gameStagesStacks.get(questId).push(new GameStage(questId, startNode));
            timeMap.put(questId, new QuestTimer());
        }
        else {
            startNode = gameStagesStacks.get(questId).peek().getNode();
        }
        return startNode;
    }

    public List<Transition> getCurrentTransitionsByQuestId(long questId){
        return gameStagesStacks.get(questId).peek().getNodeTransitions();
    }

    public Node getNodeByQuestId(long questId){
        return gameStagesStacks.get(questId).peek().getNode();
    }

    public Quest getQuest(long questId){
        return questDAO.getById(questId);
    }

    public void setNewGameStage(long questId, Node node){
        gameStagesStacks.get(questId).push(new GameStage(questId, node));
    }

    public void refresh(long questId) {
        gameStagesStacks.remove(questId);
    }

    public Resource getResourceById(long resourceId){
        return resourceDAO.getById(resourceId);
    }

    public Node getPreviousNode(long questId) {
        return gameStagesStacks.get(questId).pop().getNode();
    }

    public boolean started(long questId) {
        return gameStagesStacks.get(questId).size() > 1;
    }

    public List<Node> getNodesList(long questId) {
        List<GameStage> gameStages = new ArrayList<GameStage>(gameStagesStacks.get(questId));
        List<Node> nodes = new ArrayList<Node>();
        for (GameStage gameStage:gameStages){
            nodes.add(gameStage.getNode());
        }
        return nodes;
    }

    public void incQuestTimer(long questId) {
        timeMap.get(questId).increment();
    }

    public String getQuestTimerString(long questId) {
        QuestTimer qt = timeMap.get(questId);
        if(qt==null){
            return "";
        }
        else {
            return qt.toString();
        }
    }
}
