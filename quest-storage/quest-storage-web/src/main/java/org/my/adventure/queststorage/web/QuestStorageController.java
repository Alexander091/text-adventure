package org.my.adventure.queststorage.web;

import org.my.adventure.queststorage.ejb.QuestStorageBean;
import org.my.adventure.queststorage.ejb.QuestWrapper;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by al on 18.02.2016.
 */
@ManagedBean(name = "questStorageController")
@ViewScoped
public class QuestStorageController implements Serializable{

    @EJB
    QuestStorageBean questStorageBean;

    private String genreFilter = QuestStorageBean.ALL_GENRES;

    private List<String> genres;

    public List<String> getGenres() {
        return genres;
    }

    public String getGenreFilter() {
        return genreFilter;
    }

    public void setGenreFilter(String genreFilter) {
        this.genreFilter = genreFilter;
    }

    ExternalContext externalContext = null;
    Map<String, Object> sessionMap = null;

    public QuestStorageController() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        sessionMap = externalContext.getSessionMap();
        initLayout();
    }

    public void setLayout(String layout) {
        sessionMap.put("storageLayout", layout);
    }

    public void initLayout(){
        String layout = (String) sessionMap.get("storageLayout");
        if (null == layout){
            setLayout("grid");
        }
    }

    public void changeLayout(){
        String layout = (String) sessionMap.get("storageLayout");
        if (null == layout)
            layout = "scroller";
        else {
            if (layout.equals("scroller"))
                layout = "grid";
            else
                layout = "scroller";
        }
        sessionMap.put("storageLayout", layout);
    }

    public String getLayout() {
        String layout = (String) sessionMap.get("storageLayout");
        if (null == layout)
            layout = "grid";
        return layout;
    }

    public List<QuestWrapper> getQuests(){
        return questStorageBean.getQuests(genreFilter);
    }

    public void deleteQuest(Long id){
        questStorageBean.deleteQuest(id);
    }

    private TagCloudModel model;

    @PostConstruct
    public void init() {
        model = new DefaultTagCloudModel();
        model.addTag(new DefaultTagCloudItem("Все", "#", 5));
        model.addTag(new DefaultTagCloudItem("Фэнтези", 3));
        model.addTag(new DefaultTagCloudItem("Sci-fi", "#", 2));
        model.addTag(new DefaultTagCloudItem("Хоррор", 2));
        model.addTag(new DefaultTagCloudItem("Детектив", 1));
        model.addTag(new DefaultTagCloudItem("Slice of life", "#", 1));

        genres = new ArrayList<>();
        for (TagCloudItem item : model.getTags()) {
            genres.add(item.getLabel());
        }
    }

    public TagCloudModel getModel() {
        return model;
    }

    public void selectGenre(SelectEvent event) {
        TagCloudItem item = (TagCloudItem) event.getObject();
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", item.getLabel());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        genreFilter = item.getLabel();
    }
}
