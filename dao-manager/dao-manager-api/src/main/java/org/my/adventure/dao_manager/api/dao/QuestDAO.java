package org.my.adventure.dao_manager.api.dao;

import org.my.adventure.dao_manager.api.entities.Quest;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface QuestDAO extends CommonDAO<Quest> {
    Quest getTestObject();

    List<Quest> getAllByGenre(String genre);

    void deleteById(Long id);
}
