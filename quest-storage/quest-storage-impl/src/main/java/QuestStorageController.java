import org.hibernate.HibernateError;
import org.my.adventure.dao_manager.api.dao.*;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by al on 30.12.2015.
 */

@ManagedBean(name = "questStorageController")
@SessionScoped
public class QuestStorageController implements Serializable{

    @EJB
    QuestDAO questDAO;

    @EJB
    ResourceDAO resourceDAO;

    ExternalContext externalContext = null;//TODO: fix layout bug
    Map<String, Object> sessionMap = null;

    public QuestStorageController() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        sessionMap = externalContext.getSessionMap();
        initLayout();
    }

    public String getLayout() {
        String layout = (String) sessionMap.get("storageLayout");
        if (null == layout)
            layout = "grid";
        return layout;
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
        try{
            switch (layout){
                case "scroller": layout = "grid";
                    break;
                case "grid": layout = "scroller";
                    break;
                default: layout = "scroller";
                    break;
            }
        }catch (NullPointerException e){//TODO: add logger
            layout = "grid";
        }
        sessionMap.put("storageLayout", layout);
    }

    public Integer scaleQuestRating(Quest quest){
        return Math.round(quest.getRating() / 20);
    }


    //TODO: include resources data into sql script, user->users, users data
    public List<Quest> getQuests(){
        List<Quest> quests = questDAO.getAll();
        for (Quest quest : quests) {
            if (quest.getImage() == null)
                quest.setImage(resourceDAO.getById(0L));
        }
        quests.sort(new Comparator<Quest>() {
            public int compare(Quest o1, Quest o2) {
                return o1.getId() > o2.getId() ? 1 : (o1.getId() < o2.getId()) ? -1 : 0;
            }
        });
        return quests;
    }

    public void deleteQuest(Long id){
        System.out.println("deleting " + id+ " " + questDAO.getById(id));
        try {
            Quest quest = questDAO.getById(id);
            questDAO.delete(quest);
        }catch (HibernateError h){
            System.out.println(h.getMessage());
        }
    }
}
