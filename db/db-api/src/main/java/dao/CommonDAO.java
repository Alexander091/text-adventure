package dao;

import entities.Common;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface CommonDAO<T extends Common> {
    T get(Long id);
    void saveOrUpdate(T object);
    List<T> list();
}
