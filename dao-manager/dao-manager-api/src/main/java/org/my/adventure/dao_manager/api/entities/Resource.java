package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface Resource extends Common {
    TypeOfResource getType();
    void setType(TypeOfResource type);
    String getName();
    void setName(String name);
    String getPath();
    void setPath(String path);
    Quest getQuestByQuestId();
    void setQuestByQuestId(Quest questByQuestId);
}
