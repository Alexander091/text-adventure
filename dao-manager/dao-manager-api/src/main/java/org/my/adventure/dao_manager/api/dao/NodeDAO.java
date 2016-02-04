package org.my.adventure.dao_manager.api.dao;

import org.my.adventure.dao_manager.api.entities.Node;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface NodeDAO extends CommonDAO<Node> {
    List<Node> getNodesByQuestId(long questId);
}
