package org.my.adventure.questeditor.web.rest;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.dao.TransitionDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.impl.beans.GraphEditorBean;
import org.my.adventure.questeditor.impl.beans.QuestBean;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by dimko_000 on 04.02.2016.
 */
@Path("/quest")
public class QuestRestController {
    @Inject
    GraphEditorBean graphEditorBean;

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestGraphById(@PathParam("id") Long id) {
        graphEditorBean.loadQuest(id);
        return graphEditorBean.getQuestGraphInJson();
    }
}
