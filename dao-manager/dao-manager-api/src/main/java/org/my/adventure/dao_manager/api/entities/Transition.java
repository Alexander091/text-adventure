package org.my.adventure.dao_manager.api.entities;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface Transition extends Common {
    String getCondition();
    void setCondition(String condition);
    Node getNodeByToNode();
    void setNodeByToNode(Node nodeByToNode);
    Node getNodeByFromNode();
    void setNodeByFromNode(Node nodeByFromNode);
}
