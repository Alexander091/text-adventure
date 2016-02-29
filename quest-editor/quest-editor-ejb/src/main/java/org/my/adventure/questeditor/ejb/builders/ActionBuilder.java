package org.my.adventure.questeditor.ejb.builders;

import org.my.adventure.dao_manager.api.entities.Action;
import org.my.adventure.dao_manager.api.entities.Resource;
import org.my.adventure.dao_manager.api.entities.TypeOfAction;
import org.my.adventure.questeditor.ejb.beans.ResourceEditorBean;
import org.my.adventure.questeditor.ejb.beans.TypeOfActionBean;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimko_000 on 29.02.2016.
 */

public class ActionBuilder {
    @EJB
    TypeOfActionBean typeOfActionBean;
    @EJB
    ResourceEditorBean resourceEditorBean;
    public static Action buildAction(String data, Resource resource, TypeOfAction typeOfAction) {
        JSONObject jsonData = new JSONObject(data);
        Action action = new Action();
        action.setResource(resource);
        action.setType(typeOfAction);
        return action;
    }
    public static List<Action> buildActions(String nodeData) {
        ResourceEditorBean resourceEditorBean = null;
        TypeOfActionBean typeOfActionBean = null;
        try {
            resourceEditorBean = (ResourceEditorBean) new InitialContext().lookup("java:global/TextAdventure/TextAdventure/ResourceEditorBean!org.my.adventure.questeditor.ejb.beans.ResourceEditorBean");
            typeOfActionBean = (TypeOfActionBean) new InitialContext().lookup("java:global/TextAdventure/TextAdventure/TypeOfActionBean!org.my.adventure.questeditor.ejb.beans.TypeOfActionBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        List<Action> actions = new ArrayList<Action>();
        JSONObject jsonData = new JSONObject(nodeData);
        JSONArray actionsJsonArray = jsonData.getJSONArray("actions");
        for(int i = 0; i<actionsJsonArray.length(); i++) {
            Action action = new Action();
            Long resourceId = Long.parseLong((String) ((JSONObject) actionsJsonArray.get(i)).get("resource_id"));
            action.setResource(resourceEditorBean.getById(resourceId));
            Long actionTypeId = Long.parseLong((String)((JSONObject)actionsJsonArray.get(i)).get("action_type_id"));
            action.setType(typeOfActionBean.getById(actionTypeId));
            actions.add(action);
        }
        return actions;
    }
}
