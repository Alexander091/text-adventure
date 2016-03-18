package org.my.adventure.dao_manager.api.dao;

import org.my.adventure.dao_manager.api.entities.Resource;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface ResourceDAO extends CommonDAO<Resource> {
    List<Resource> getResources(Long questId, Long typeOfResourceId);
    List<Resource> getPartialResources(Long questId, Long typeOfResourceId);
    void deleteById(Long resourceId);
}
