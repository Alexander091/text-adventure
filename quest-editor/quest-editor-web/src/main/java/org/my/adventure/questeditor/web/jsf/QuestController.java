package org.my.adventure.questeditor.web.jsf;

import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.questeditor.impl.beans.QuestBean;
import org.primefaces.event.TabChangeEvent;

import javax.faces.bean.*;
import javax.faces.bean.ViewScoped;
import javax.faces.view.*;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name = "questController")
//@Named(value = "questController")
@ViewScoped
public class QuestController implements Serializable{
    private Long questId = null;
    private Integer activeIndex = 1;
    private Quest quest;
    @Inject
    QuestBean questBean;

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }

    public void loadQuest() {
        quest=questBean.getById(questId);
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
        Node node = new Node();
        node.setName("�����");
        node.setDescription("��������� �������");
        node.setPosition("100 100");
        quest.setStartNode(node);
        questId = questBean.saveOrUpdate(quest);
        return "editor?faces-redirect=true&questId="+questId;
    }
    public void updateQuest() {
        questId = questBean.saveOrUpdate(quest);
    }
    public void onTabChange(TabChangeEvent event) {
        if(event.getTab().getTitle().equals("�����")) {
            loadQuest();
        }
    }
    public List<Node> getAllNodes() {
        if(questId==null)
            return new ArrayList<Node>();
        return questBean.getAllNodes(questId);
    }
}
