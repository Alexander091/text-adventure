package org.my.adventure.questeditor.impl.views;

import org.my.adventure.dao_manager.api.entities.Transition;
import org.primefaces.json.JSONObject;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public class TransitionView extends EntityView<Transition> {
    private NodeView from;
    private NodeView to;
    public TransitionView(String viewId, NodeView from, NodeView to, Transition transition) {
        this.viewId = viewId;
        this.from = from;
        this.to = to;
        entity = transition;
    }

    public NodeView getTo() {
        return to;
    }

    public void setTo(NodeView to) {
        this.to = to;
    }

    public NodeView getFrom() {
        return from;
    }

    public void setFrom(NodeView from) {
        this.from = from;
    }

    @Override
    public JSONObject getJsonOfView() {
        JSONObject transitionJson = new JSONObject();
        transitionJson.put("group", "edges");
        JSONObject dataJson = new JSONObject();
        dataJson.put("id", viewId);
        dataJson.put("source", from.getViewId());
        dataJson.put("target", to.getViewId());
        transitionJson.put("data", dataJson);
        return transitionJson;
    }

    @Override
    public JSONObject getJsonOfEntity() {
        JSONObject transitionJson = new JSONObject();
        transitionJson.put("id", entity.getId());
        transitionJson.put("name", entity.getName());
        transitionJson.put("sourceId", entity.getNodeByFromNode().getId());
        transitionJson.put("targetId", entity.getNodeByToNode().getId());
        return transitionJson;
    }
}
