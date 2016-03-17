package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.entities.*;
import org.my.adventure.questgame.impl.nodewr_builder.NodeWrapperDirector;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.QuestWrapper;

import javax.ejb.EJB;
import javax.ejb.Stateful;
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

    public void reset(long questId) {
        gameStagesBean.reset(questId);
    }

    public NodeWrapper goBack(long questId) {
        return NodeWrapperDirector.constructNodeWrapper(gameStagesBean.getPreviousNode(questId));
    }

    public boolean started(long questId) {
        return gameStagesBean.started(questId);
    }

    public List<NodeWrapper> getStack(long questId) {
        List<NodeWrapper> list =  new ArrayList<NodeWrapper>();
        for(Node node : gameStagesBean.getNodesList(questId)){
            list.add(NodeWrapperDirector.constructNodeWrapper(node));
        }
        return list;
    }

    /*public QuestTimer getQtuestTimer(long questId) {
        return gameStagesBean.getQuestTimer(questId);
    }*/

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