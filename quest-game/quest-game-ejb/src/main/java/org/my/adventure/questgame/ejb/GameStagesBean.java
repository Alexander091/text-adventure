package org.my.adventure.questgame.ejb;

import org.my.adventure.questgame.impl.GameStage;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 17.02.2016.
 */

@Stateful
public class GameStagesBean {
    private List<GameStage> gameStages;

    @PostConstruct
    void init(){
        gameStages = new ArrayList<GameStage>();
    }

    public List<GameStage> getGameStages() {
        return gameStages;
    }

    public boolean isQuestStart(long questId){
        boolean flag = false;
        for(GameStage g : gameStages){
            if(g.getQuestId()==questId){
                flag=true;
                break;
            }
        }
        return flag;
    }

    public GameStage getGameStageByQuestId(long questId){
        GameStage gameStage=null;
        for(GameStage g : gameStages){
            if(g.getQuestId()==questId){
                gameStage = g;
                break;
            }
        }
        return gameStage;
    }

    public void setNewGameStage(int indexOfGameStage, GameStage gameStage){
        gameStages.set(indexOfGameStage,gameStage);
    }
}
