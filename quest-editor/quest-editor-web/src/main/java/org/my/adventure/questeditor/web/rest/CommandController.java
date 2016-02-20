package org.my.adventure.questeditor.web.rest;

import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.questeditor.impl.beans.GraphEditorBean;
import org.my.adventure.questeditor.impl.views.NodeView;
import org.my.adventure.questeditor.impl.views.TransitionView;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by dimko_000 on 18.02.2016.
 */
@Path("/command")
@SessionScoped
public class CommandController implements Serializable{
    @Inject
    GraphEditorBean graphEditorBean;

    @GET
    @Path("/quest/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestGraphById(@PathParam("id") Long id) {
        graphEditorBean.loadQuest(id);
        Graph<NodeView,TransitionView> viewGraph = graphEditorBean.getViewGraph();
        JSONArray graphJson = new JSONArray();
        for(NodeView nodeView : viewGraph.vertexSet())
            graphJson.put(nodeView.getJsonOfView());
        for (TransitionView transitionView : viewGraph.edgeSet())
            graphJson.put(transitionView.getJsonOfView());
        return graphJson.toString();
    }

    @POST
    @Path("/node/post")
    @Produces(MediaType.APPLICATION_JSON)
    public String postNode(String data) {
        return graphEditorBean.addNode(data).getJsonOfView().toString();
    }
    @GET
    @Path("/node/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllNodesOfQuest() {
        Set<NodeView> nodeViews = graphEditorBean.getAllNodes();
        JSONArray jsonArray = new JSONArray();
        for (NodeView nodeView : nodeViews)
            jsonArray.put(nodeView.getJsonOfView());
        return jsonArray.toString();
    }
    @POST
    @Path("/transition/post")
    @Produces(MediaType.APPLICATION_JSON)
    public String postTransition(String data) {
        return graphEditorBean.addTransition(data).getJsonOfView().toString();
    }
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public String save() {
        return graphEditorBean.save();
    }

    @POST
    @Path("/undo")
    @Produces(MediaType.APPLICATION_JSON)
    public String undo() {
        return graphEditorBean.undo();
    }
}
