package org.my.adventure.questeditor.web.jsf;

import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.questeditor.ejb.beans.NodeBean;
import org.my.adventure.questeditor.ejb.beans.QuestEditorBean;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name = "questEditorController")
//@Named(value = "questController")
@ViewScoped
public class QuestEditorController implements Serializable{
    private Long questId = null;
    private Integer activeIndex = 1;
    private Quest quest;
    @EJB
    QuestEditorBean questEditorBean;
    @EJB
    NodeBean nodeBean;
    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }

    public void loadQuest() {
        quest= questEditorBean.getById(questId);
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
    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
        activeIndex = 0;
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
    public String saveQuest() {
        questEditorBean.saveOrUpdate(quest);
        Node node = new Node();
        node.setName("Some quest");
        node.setDescription("editor quest");
        node.setPosition("250 100");
        node.setQuestByQuestId(quest);
        nodeBean.saveOrUpdate(node);
        quest.setStartNode(node);
        questEditorBean.saveOrUpdate(quest);
        questId=quest.getId();
        return "editor?faces-redirect=true&questId="+questId;
    }
    public void updateQuest() {
        questEditorBean.saveOrUpdate(quest);
    }
}
