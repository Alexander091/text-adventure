import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

/**
 * Created by al on 30.12.2015.
 */

@ManagedBean
public class QuestStorageController {

    ArrayList<Quest> quests;

    public QuestStorageController(){
        quests = new ArrayList<Quest>();
        quests.add(new Quest("Quest 1", "Genre", 42, QuestDifficulty.Easy));
        quests.add(new Quest("Find the Queen!", "Detective", 87, QuestDifficulty.Hard));
        quests.add(new Quest("Escape from tower", "Detective", 64, QuestDifficulty.Medium));
        quests.add(new Quest("Dungeon master", "Fantasy", 96, QuestDifficulty.Easy));
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }
}
