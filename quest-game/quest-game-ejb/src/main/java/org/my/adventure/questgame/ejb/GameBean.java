package org.my.adventure.questgame.ejb;

import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.dao.TypeOfResourceDAO;
import org.my.adventure.dao_manager.api.entities.*;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.QuestWrapper;
import org.my.adventure.questgame.impl.wrappers.TransitionWrapper;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.io.File;
import java.io.FileInputStream;
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

    //Actions:
    private Action getActionByType(long questId, String type){
        Action action = null;
        List<Action> actions = gameStagesBean.getNodeByQuestId(questId).getActions();
        if (!actions.isEmpty()) {
            for (Action a : actions) {
                if (a.getType().getName().equals(type)) {
                    action = a;
                }
            }
        }
        return action;
    }

    public byte[] getImage(long questId){
        byte[] image = null;
        Action imageAction = getActionByType(questId,"image");
        if (imageAction!=null){
            image = imageAction.getResource().getData();
        }


        return image;
    }

/*

    @EJB
    ResourceDAO resourceDAO;

    @EJB
    TypeOfResourceDAO typeOfResourceDAO;

    public void input(long questId){

        File file = new File("C:\\forest.jpg");
        byte[] imageData = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resource res = new Resource();
        res.setName("forest");
        res.setType(typeOfResourceDAO.getById(1L));
        res.setData(imageData);
        res.setQuestByQuestId(gameStagesBean.getQuest(questId));
        resourceDAO.saveOrUpdate(res);
    }
*/

}