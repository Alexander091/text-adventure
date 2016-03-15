package org.my.adventure.questeditor.web.jsf;

import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Resource;
import org.my.adventure.questeditor.ejb.beans.NodeBean;
import org.my.adventure.questeditor.ejb.beans.QuestEditorBean;
import org.my.adventure.questeditor.ejb.beans.ResourceEditorBean;
import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name = "questEditorController")
//@Named(value = "questController")
@SessionScoped
public class QuestEditorController implements Serializable{
    private Long questId = null;
    private Integer activeIndex = 1;
    private Quest quest;
    private static long TYPE_IMAGE = 1;
    @EJB
    QuestEditorBean questEditorBean;
    @EJB
    NodeBean nodeBean;
    @EJB
    ResourceEditorBean resourceEditorBean;
    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }

    public Resource getDefaultPicture() {
        return resourceEditorBean.getById(0L);
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

    public Resource getImage() {
        return quest.getImage();
    }
    public void setImage(Resource image) {
        quest.setImage(image);
       // RequestContext.getCurrentInstance().update("tabs:questForm:questPicture");
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
    public List<Resource> getAllAvailablePictures() {
        return resourceEditorBean.getResourcesList(questId, TYPE_IMAGE);
    }
    public void onTabChange(TabChangeEvent event) {
        if(event.getTab().getId().equals("questTab"))
            RequestContext.getCurrentInstance().update("tabs:questForm:questPictureMenu");
    }
    public String quit() {
        quest=null;
        questId=null;
        activeIndex = 1;
        return "/quest-storage/storage?faces-redirect=true";
    }
    public StreamedContent getByteImage(){
        StreamedContent streamedContent;
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//             So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            streamedContent = new DefaultStreamedContent();
        }else {
//            String resourceId = context.getExternalContext().getRequestParameterMap().get("resourceId");
            Resource image = quest.getImage();
            byte[] imageData;
            if (image == null){
                imageData = resourceEditorBean.getById(0L).getData();
            }
            else
                imageData = image.getData();
            if (imageData != null) {
                streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(imageData), "image/jpg");
            } else {
                streamedContent = new DefaultStreamedContent();
            }
        }
        return streamedContent;
    }
}
