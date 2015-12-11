package entities;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface Item extends Common {
    Quest getQuest();
    void setQuest(Quest quest);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);

}
