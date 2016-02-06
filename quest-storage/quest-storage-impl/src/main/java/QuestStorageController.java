import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateError;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by al on 30.12.2015.
 */

@ManagedBean
@ViewScoped
public class QuestStorageController implements Serializable{

    private final Logger logger = LogManager.getLogger(QuestStorageController.class);

    @EJB
    QuestDAO questDAO;
    Quest quest = null;

    public QuestStorageController(){
    }

    @PostConstruct
    private void init(){
        quest = questDAO.getById(123L);
    }

    public List<Quest> getQuests(){
        return questDAO.getAll();
    }

    public void deleteQuest(Long id){
        logger.debug("deleting " + id + " " + questDAO.getById(id).getName());
        System.out.println("deleting " + id+ " " + questDAO.getById(id));
        try {
            questDAO.delete(questDAO.getById(id));
        }catch (HibernateError h){
            logger.error(h.getMessage());
        }
    }
}
