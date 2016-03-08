package org.my.adventure.questeditor.ejb.beans;

import org.my.adventure.dao_manager.api.entities.Common;
import org.my.adventure.dao_manager.api.entities.Node;

import javax.ejb.Stateless;

/**
 * Created by dimko_000 on 28.02.2016.
 */
public abstract class CommonBean<T extends Common> {
        public abstract T getById(Long id);

        public abstract void saveOrUpdate(T object);

        public abstract void delete(T object);
}
