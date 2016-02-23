package org.my.adventure.questgame.impl.wrappers;


/**
 * Created by Максим on 18.02.2016.
 */
public class QuestWrapper {
    private long questId;
    private String questName;

    public QuestWrapper(long questId, String questName){
        this.questId = questId;
        this.questName = questName;
    }

    public long getQuestId() {
        return questId;
    }

    public String getQuestName() {
        return questName;
    }
}
