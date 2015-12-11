package org.my.adventure.dao_manager.api.entities;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface Action extends Common {
    List<Action> getNextActions();
    void setNextActions(List<Action> nextActions);
    Resource getResource();
    void setResource(Resource resource);
    Item getItem();
    void setItem(Item item);
    Stat getStat();
    void setStat(Stat stat);
    TypeOfAction getType();
    void setType(TypeOfAction type);
    String getCondition();
    void setCondition(String condition);
    Float getValue();
    void setValue(Float value);
    Action getParent();
    void setParent(Action actionByParent);
    Node getNode();
    void setNode(Node node);
}
