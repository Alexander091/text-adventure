package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.questgame.impl.GameStage;
import org.my.adventure.questgame.impl.wrappers.QuestWrapper;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Created by Максим on 17.02.2016.
 */

@Stateful
public class CurrentGameStageBean {
    private Node node;
    private QuestWrapper wrappedQuest; //иначе, когда мы будем сохранять GameStage и брать id квеста через quest.getId(), то каждый раз hibernate будет дедать лишний запрос в базу.
    private int indexOfGameStage;

    @EJB
    GameStagesBean gameStagesBean;
    @EJB
    QuestDAO questDAO;
    @EJB
    NodeDAO nodeDAO;

   /* @PostConstruct
    void init(){
        loadGameByQuestId(123L);
    }*/

    public void loadGameByQuestId(long questId){
        GameStage gameStage = gameStagesBean.getGameStageByQuestId(questId);
        Quest quest = questDAO.getById(questId);
        wrappedQuest = new QuestWrapper(questId, quest.getName());

        if(gameStage!=null){
            node = gameStage.getNode();
        }
        else {
            node = quest.getStartNode();
            gameStage = new GameStage(questId, node);
            gameStagesBean.getGameStages().add(gameStage);
            indexOfGameStage = gameStagesBean.getGameStages().indexOf(gameStage);
        }

    }


    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
        gameStagesBean.setNewGameStage(indexOfGameStage, new GameStage(wrappedQuest.getQuestId(), node));
    }

    public QuestWrapper getWrappedQuest() {
        return wrappedQuest;
    }
}
