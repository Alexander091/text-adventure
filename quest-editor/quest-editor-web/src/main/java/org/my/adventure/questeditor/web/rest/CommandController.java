package org.my.adventure.questeditor.web.rest;

import org.my.adventure.questeditor.impl.beans.GraphEditorBean;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by dimko_000 on 18.02.2016.
 */
@Path("/command")
public class CommandController {
    @Inject
    GraphEditorBean graphEditorBean;

    @GET
    @Path("/quest/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestGraphById(@PathParam("id") Long id) {
        graphEditorBean.loadQuest(id);
        return graphEditorBean.getQuestGraphInJson();
    }

    @POST
    @Path("/node/post")
    @Produces(MediaType.APPLICATION_JSON)
    public String postNode(String data) {
        return graphEditorBean.addNode(data);
    }
}
