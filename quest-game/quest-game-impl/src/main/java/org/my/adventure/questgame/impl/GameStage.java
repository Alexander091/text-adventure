package org.my.adventure.questgame.impl;

/**
 * Created by Максим on 17.02.2016.
 */

public class GameStage {
    
    private long questId;
    private long currentNodeId;
    
    public GameStage (long questId, long currentNodeId){
        this.questId = questId;
        this.currentNodeId =currentNodeId;
    }


    public void setQuestId(long questId) {
        if(questId!=0L) {
            this.questId = questId;
        }
        else throw new NullPointerException("QuestId is null");
    }

    public long getQuestId() {
        return questId;
    }
    
    public void setCurrentNodeId(long currentNodeId) {
        if(currentNodeId!=0L) {
            this.currentNodeId = currentNodeId;
        }
        else throw new NullPointerException("CurrentNodeId is null");
    }

    public long getCurrentNodeId() {
        return currentNodeId;
    }

  
}
