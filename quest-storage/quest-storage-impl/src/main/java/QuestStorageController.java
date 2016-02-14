import org.hibernate.HibernateError;
import org.my.adventure.dao_manager.api.dao.*;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Resource;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * Created by al on 30.12.2015.
 */

@ManagedBean(name = "questStorageController")
@SessionScoped
public class QuestStorageController implements Serializable{

    @EJB
    QuestDAO questDAO;

    String layout = "scroller";

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public QuestStorageController(){
    }

    public List<Quest> getQuests(){
        List<Quest> quests = questDAO.getAll();
        /*quests.sort(new Comparator<Quest>() {
            public int compare(Quest o1, Quest o2) {
                return o1.getId() > o2.getId() ? 1 : (o1.getId() < o2.getId()) ? -1 : 0;
            }
        });*/
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
