package org.my.adventure.questeditor.impl;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dimko_000 on 03.02.2016.
 */
@Stateful
public class QuestBean implements Serializable{
    @EJB
    QuestDAO questDAO;
    @EJB
    NodeDAO nodeDAO;
    Quest quest = null;
    public void loadQuest(long id) {
       quest = questDAO.getById(id);
    }
    public String getQuestData() {
        return "[{\"group\": \"nodes\", \"data\": {\"id\": \"1\"}, \"position\": { \"x\": 100, \"y\": 100}}]";
    }
    public String getName() {
        return quest.getName();
    }
    public String getDescription(){
        return quest.getDescription();
    }
    public String getGenre() {
        return quest.getGenre();
    }
    public int getVersion() {
        return quest.getVersion();
    }
    public int getAgeLimit() {
        return quest.getAgeLimit();
    }
    public void setName(String name) {
        quest.setName(name);
    }
    public void setDescription(String description) {
        quest.setDescription(description);
    }
    public void setGenre(String genre) {
        quest.setGenre(genre);
    }
    public void setVersion(int version) {
        quest.setVersion(version);
    }
    public void setAgeLimit(int ageLimit) {
        quest.setAgeLimit(ageLimit);
    }
    public void saveQuest() {
        questDAO.saveOrUpdate(quest);
    }
    public List<Node> getAllNodes() {
        List<Node> nodes = nodeDAO.getNodesByQuestId(quest.getId());
        return nodes;
    }
}
