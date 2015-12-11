package com.textadventure.core.api;

/**
 * Created by dimko_000 on 10.12.2015.
 */
public interface QuestDao {
    QuestEntity getQuestById(int id);
    void saveQuest(QuestEntity quest);
    void deleteQuest(QuestEntity quest);
    void updateQuest(QuestEntity quest);
}
