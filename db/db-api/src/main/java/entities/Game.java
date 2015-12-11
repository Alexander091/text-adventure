package entities;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface Game extends Common {
    List<SavedItem> getSavedItems();
    void setSavedItems(List<SavedItem> savedItems);
    List<SavedStat> getSavedStats();
    void setSavedStats(List<SavedStat> savedStats);
    User getUser();
    void setUser(User user);
    Quest getQuest();
    void setQuest(Quest quest);
}
