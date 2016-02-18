package org.my.adventure.questeditor.web.rest;

import org.my.adventure.questeditor.impl.beans.QuestBean;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dimko_000 on 18.02.2016.
 */
@Path("/command")
public class CommandController {
    @EJB
    QuestBean questBean;

    @GET
    @Path("/quest/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestGraphById(@PathParam("id") Long id) {
        questBean.loadQuest(id);
        return questBean.getQuestGraphInJson();
    }

}
