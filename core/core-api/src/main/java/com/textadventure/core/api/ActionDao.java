package com.textadventure.core.api;
/**
 * Created by dimko_000 on 10.12.2015.
 */
public interface ActionDao {
    ActionEntity getActionById(int id);
    List<ActionEntity> getActionsByNode(NodeEntity node);
    void saveAction(ActionEntity action);
    void deleteAction(ActionEntity action);
    void updateAction(ActionEntity action);
}
