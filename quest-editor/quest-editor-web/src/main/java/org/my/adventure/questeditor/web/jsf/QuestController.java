package org.my.adventure.questeditor.web.jsf;

import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.questeditor.impl.QuestBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.*;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name = "questController")
//@Named(value = "questController")
@SessionScoped
public class QuestController implements Serializable{
    private long questId = -1;
    private String name = null;
    private String description = null;
    private String genre = null;
    private int version = -1;
    private int ageLimit = -1;
//    private boolean isInitialized = false;
    @Inject
    QuestBean questBean = null;
    public void init() {
        loadQuest(questId);
    }
    public void loadQuest(long id) {
        questBean.loadQuest(id);
    }
    public String getName() {
        return questBean.getName();
    }
    public String getDescription(){
        return questBean.getDescription();
    }
    public String getGenre() {
        return questBean.getGenre();
    }
    public int getVersion() {
        return questBean.getVersion();
    }
    public int getAgeLimit() {
        return questBean.getAgeLimit();
    }
    public long getQuestId() {
        return questId;
    }

    public void setQuestId(long questId) {
        this.questId = questId;
    }
    public void setName(String name) {
        this.name = name;

    }
    public void setDescription(String description) {
        this.description = description;

    }
    public void setGenre(String genre) {
        this.genre = genre;

    }
    public void setVersion(int version) {
        this.version = version;

    }
    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;

    }
    public void saveQuest() {
        questBean.setName(name);
        questBean.setDescription(description);
        questBean.setGenre(genre);
        questBean.setAgeLimit(ageLimit);
        questBean.setVersion(version);
        questBean.saveQuest();
    }
    public String getData() {
        return questBean.getQuestData();
    }
    public List<Node> getAllNodes() {
        return questBean.getAllNodes();
    }
}
