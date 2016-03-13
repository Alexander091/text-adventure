package org.my.adventure.questeditor.ejb.views;

import org.my.adventure.dao_manager.api.entities.Action;
import org.my.adventure.dao_manager.api.entities.Node;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import java.util.List;

/**
 * Created by dimko_000 on 18.02.2016.
 */
public class NodeView extends EntityView<Node> {
    public NodeView(String viewId, Node node) {
        this.viewId = viewId;
        this.entity = node;
    }
    @Override
    public JSONObject getJsonOfView() {
        JSONObject nodeJson = new JSONObject();
        nodeJson.put("group", "nodes");
        JSONObject dataJson = new JSONObject();
        dataJson.put("id", viewId);
        dataJson.put("name", entity.getName());
        nodeJson.put("data", dataJson);
        String position = entity.getPosition();
        Double posx = Double.parseDouble(position.split(" ")[0]);
        Double posy = Double.parseDouble(position.split(" ")[1]);
        JSONObject positionJson = new JSONObject();
        positionJson.put("x", posx);
        positionJson.put("y", posy);
        nodeJson.put("position", positionJson);
        return nodeJson;
    }

    @Override
    public JSONObject getJsonOfEntity() {
        JSONObject nodeJson = new JSONObject();
        nodeJson.put("id", viewId);
        nodeJson.put("name", entity.getName());
        nodeJson.put("description", entity.getDescription());
        List<Action> actionList = entity.getActions();
        JSONArray actionsJsonArray = new JSONArray();
        for (Action action : actionList) {
            JSONObject actionJson = new JSONObject();
            actionJson.put("id", action.getId());
            actionJson.put("resource_id", action.getResource().getId());
            actionJson.put("resource_name", action.getResource().getName());
            actionJson.put("action_type_id", action.getType().getId());
            actionJson.put("action_type_name", action.getType().getName());
            actionsJsonArray.put(actionJson);
        }
        nodeJson.put("actions", actionsJsonArray);
        return nodeJson;
    }

    public JSONObject getJsonForDropDownMenu(boolean selected) {
        JSONObject nodeJson = new JSONObject();
        nodeJson.put("id", viewId);
        nodeJson.put("name", entity.getName());
        nodeJson.put("description", entity.getDescription());
        nodeJson.put("selected", selected);
        return nodeJson;
    }
}
