package com.textadventure.core.api;

/**
 * Created by dimko_000 on 10.12.2015.
 */
public interface ConditionDao {
    ConditionEntity getConditionById(int id);
    void saveCondition(ConditionEntity condition);
    void deleteCondition(ConditionEntity condition);
    void updateCondition(ConditionEntity condition);
}
