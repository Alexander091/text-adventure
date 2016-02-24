package org.my.adventure.queststorage.web;

import org.my.adventure.queststorage.impl.QuestBean;
import org.my.adventure.queststorage.impl.QuestWrapper;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by al on 18.02.2016.
 */
@ManagedBean(name = "questController")
@ViewScoped
public class QuestController implements Serializable{

    @Inject
    QuestBean questBean;

    ExternalContext externalContext = null;
    Map<String, Object> sessionMap = null;

    public QuestController() {
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

        if (layout.equals("scroller"))
            layout = "grid";
        else
            layout = "scroller";

        sessionMap.put("storageLayout", layout);
    }

    public String getLayout() {
        String layout = (String) sessionMap.get("storageLayout");
        if (null == layout)
            layout = "grid";
        return layout;
    }

    public List<QuestWrapper> getQuests(){
        return questBean.getQuests();
    }

    public void deleteQuest(Long id){
        questBean.deleteQuest(id);
    }
}
