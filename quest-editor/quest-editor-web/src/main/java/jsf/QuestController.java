package jsf;

import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.questeditor.impl.QuestBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by dimko_000 on 10.12.2015.
 */
@ManagedBean(name = "questController")
@SessionScoped
public class QuestController {
    @Inject
    QuestBean questBean = null;
    @PostConstruct
    public void init() {
        loadQuest(123L);
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
    public String getData() {
        String data = questBean.getQuestData();
        return questBean.getQuestData();
    }
}//l
