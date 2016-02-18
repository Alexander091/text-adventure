package org.my.adventure.questeditor.impl.views;

import org.primefaces.json.JSONObject;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public abstract class EntityView<T> {
    protected String viewId;
    protected T entity;

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public abstract JSONObject getJsonOfView();
    public abstract JSONObject getJsonOfEntity();
}
