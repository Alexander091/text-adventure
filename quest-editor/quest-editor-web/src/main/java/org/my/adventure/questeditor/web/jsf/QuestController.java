package org.my.adventure.questeditor.web.jsf;

import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.questeditor.impl.beans.QuestBean;

import javax.faces.bean.*;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name = "questController")
//@Named(value = "questController")
@SessionScoped
public class QuestController implements Serializable{
    private Long questId = null;
//    private String name = null;
//    private String description = null;
//    private String genre = null;
//    private int version = -1;
//    private int ageLimit = -1;
////    private boolean isInitialized = false;l
    @Inject
    QuestBean questBean;
    public void init() {
        loadQuest(questId);
    }
    public void loadQuest(Long id) {
        questBean.loadQuest(id);
    }
    public String getName() {
        return questBean.getQuest().getName();
    }
    public String getDescription(){
        return questBean.getQuest().getDescription();
    }
    public String getGenre() {
        return questBean.getQuest().getGenre();
    }
    public int getVersion() {
        return questBean.getQuest().getVersion();
    }
    public int getAgeLimit() {
        return questBean.getQuest().getAgeLimit();
    }
    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }
    public void setName(String name) {
        questBean.getQuest().setName(name);
    }
    public void setDescription(String description) {
        questBean.getQuest().setDescription(description);
    }
    public void setGenre(String genre) {
        questBean.getQuest().setGenre(genre);
    }
    public void setVersion(int version) {
        questBean.getQuest().setVersion(version);
    }
    public void setAgeLimit(int ageLimit) {
        questBean.getQuest().setAgeLimit(ageLimit);
    }
    public void saveQuest() {
        questId = questBean.saveQuest();
    }
    public List<Node> getAllNodes() {
        if(questId==null)
            return new ArrayList<Node>();
        return questBean.getAllNodes();
    }
}
