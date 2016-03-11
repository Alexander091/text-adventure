package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.dao.TypeOfResourceDAO;
import org.my.adventure.dao_manager.api.entities.*;
import org.my.adventure.questgame.impl.nodewr_builder.NodeWrapperDirector;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.QuestWrapper;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.io.File;
import java.io.FileInputStream;

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
        return NodeWrapperDirector.constructNodeWrapper(node);
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
    /*@EJB
    ResourceDAO resourceDAO;

                @EJB
                TypeOfResourceDAO typeOfResourceDAO;

                public void input(long questId){
           File file = new File("C:\\sound.mp3");
                    byte[] imageData = new byte[(int) file.length()];

                        try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        fileInputStream.read(imageData);
                        fileInputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                Resource res = new Resource();
                res.setName("sound");
                res.setType(typeOfResourceDAO.getById(1L));
                res.setData(imageData);
                res.setQuestByQuestId(gameStagesBean.getQuest(questId));
                resourceDAO.saveOrUpdate(res);
            }*/
}