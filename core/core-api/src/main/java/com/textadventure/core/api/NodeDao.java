package com.textadventure.core.api;

/**
 * Created by dimko_000 on 10.12.2015.
 */
public interface NodeDao {
    NodeEntity getNodeById(int id);
    List<NodeEntity> getNodesByQuest(QuestEntity quest);
    void saveNode(NodeEntity node);
    void deleteNode(NodeEntity node);
    void updateNode(NodeEntity node);
}
