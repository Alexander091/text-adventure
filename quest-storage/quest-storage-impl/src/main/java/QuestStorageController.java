import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Quest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by al on 30.12.2015.
 */

@ManagedBean
@ViewScoped
public class QuestStorageController {

    ArrayList<LocalQuest> localQuests;

    @EJB
    QuestDAO questDAO;
    Quest quest = null;

    @PostConstruct
    private void init(){
        quest = questDAO.getById(123L);
    }

    public String getName() {
        return quest.getName();
    }

    public List<Quest> getQuests(){
        return questDAO.getAll();
    }

    public void deleteQuest(Quest quest){
//        questDAO.delete(quest);
    }

    public QuestStorageController(){
        localQuests = new ArrayList<LocalQuest>();
        localQuests.add(new LocalQuest("Quest 1", "Genre", 42, QuestDifficulty.Easy));
        localQuests.add(new LocalQuest("Find the Queen!", "Detective", 87, QuestDifficulty.Hard));
        localQuests.add(new LocalQuest("Escape from tower", "Detective", 64, QuestDifficulty.Medium));
        localQuests.add(new LocalQuest("Dungeon master", "Fantasy", 96, QuestDifficulty.Easy));
        localQuests.add(new LocalQuest("Deeper", "RPG", 56, QuestDifficulty.Easy));
        localQuests.add(new LocalQuest("Awakenings", "Fantasy", 76, QuestDifficulty.Medium));
        localQuests.add(new LocalQuest("The Hitchhiker's Guide to the Galaxy", "Sci-fi", 72, QuestDifficulty.Hard));
    }

    public ArrayList<LocalQuest> getLocalQuests() {
        return localQuests;
    }

    public void setLocalQuests(ArrayList<LocalQuest> localQuests) {
        this.localQuests = localQuests;
    }

}
