package org.my.adventure.queststorage.web;

import org.my.adventure.queststorage.ejb.QuestStorageBean;
import org.my.adventure.queststorage.ejb.QuestWrapper;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
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
        return questStorageBean.getQuests();
    }

    public void deleteQuest(Long id){
        questStorageBean.deleteQuest(id);
    }
}
