package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.questgame.impl.GameStage;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Created by Максим on 17.02.2016.
 */

@Stateful
public class CurrentGameStageBean {
    private Quest quest;
    private Node node;
    long questId; //иначе, когда мы будем сохранять GameStage и брать id квеста через quest.getId(), то каждый раз hibernate будет дедать лишний запрос в базу.
    private int indexOfGameStage;

    @EJB
    GameStagesBean gameStagesBean;
    @EJB
    QuestDAO questDAO;
    @EJB
    NodeDAO nodeDAO;

    @PostConstruct
    void init(){
        loadGameByQuestId(123L);
    }

    public void loadGameByQuestId(long questId){
        GameStage gameStage = gameStagesBean.getGameStageByQuestId(questId);
        this.questId = questId;
        if(gameStage!=null){
            quest = questDAO.getById(gameStage.getQuestId());
            node = gameStage.getNode();
        }
        else {
            quest = questDAO.getById(questId);
            node = quest.getStartNode();
            gameStage = new GameStage(questId, node);
            gameStagesBean.getGameStages().add(gameStage);
            indexOfGameStage = gameStagesBean.getGameStages().indexOf(gameStage);
        }

    }

    public Quest getQuest() {
        return quest;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
        gameStagesBean.setNewGameStage(indexOfGameStage, new GameStage(questId, node));
    }
}
