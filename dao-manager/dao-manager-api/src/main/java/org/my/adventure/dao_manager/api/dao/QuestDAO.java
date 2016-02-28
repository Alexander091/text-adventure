package org.my.adventure.dao_manager.api.dao;

import org.my.adventure.dao_manager.api.entities.Quest;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface QuestDAO extends CommonDAO<Quest> {
    Quest getTestObject();

    void deleteById(Long id);
}
