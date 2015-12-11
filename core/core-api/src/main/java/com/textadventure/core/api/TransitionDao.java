package com.textadventure.core.api;

/**
 * Created by dimko_000 on 10.12.2015.
 */
public interface TransitionDao {
    TransitionEntity getTransitionById(int id);
    List<TransitionEntity> getTransitionsByNode(NodeEntity node);
    void saveTransition(TransitionEntity transition);
    void deleteTransition(TransitionEntity transition);
    void updateTransition(TransitionEntity transition);
}
