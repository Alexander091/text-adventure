package org.my.adventure.dao_manager.api.dao;

import org.my.adventure.dao_manager.api.entities.Common;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface CommonDAO<T extends Common> {
    T getById(Long id);
    Long saveOrUpdate(Common object);
    List<T> getAll();
    void delete(Common object);
}
