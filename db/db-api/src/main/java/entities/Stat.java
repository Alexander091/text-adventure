package entities;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface Stat extends Common {
    String getName();
    void setName(String name);
    Float getValue();
    void setValue(Float value);
    String getDescription();
    void setDescription(String description);
    Quest getQuestByQuestId();
    void setQuestByQuestId(Quest questByQuestId);
}
