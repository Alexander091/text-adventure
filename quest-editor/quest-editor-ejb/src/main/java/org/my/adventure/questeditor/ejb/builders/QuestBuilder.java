package org.my.adventure.questeditor.ejb.builders;

import org.my.adventure.dao_manager.api.entities.Quest;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public class QuestBuilder {
    public static Quest buildDefaultQuest() {
        Quest quest = new Quest();
        quest.setName("");
        quest.setDescription("");
        quest.setAgeLimit(12);
        quest.setVersion(1);
        quest.setGenre("");
        //Node node = new Node()
        return quest;
    }
}
