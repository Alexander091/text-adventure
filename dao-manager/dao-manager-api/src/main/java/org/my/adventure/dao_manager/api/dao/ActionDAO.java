package org.my.adventure.dao_manager.api.dao;

import org.my.adventure.dao_manager.api.entities.Action;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface ActionDAO extends CommonDAO<Action> {
    void saveAll(List<Action> actionList);
}
