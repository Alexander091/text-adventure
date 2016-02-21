package org.my.adventure.questeditor.web.rest;

import org.jgrapht.Graph;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.my.adventure.questeditor.impl.GraphUtils;
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
    @GET
    @Path("/node/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNodeById(@PathParam("id") String id) {
        NodeView nodeView = GraphUtils.getNodeViewByViewId(graphEditorBean.getViewGraph(),id);
        return nodeView.getJsonOfEntity().toString();
    }
    @POST
    @Path("/node/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateNode(@PathParam("id") String viewId, String data) {
        JSONObject outputJson = new JSONObject();
        NodeView nodeView = graphEditorBean.editNode(data, viewId);
        outputJson.put("name", nodeView.getEntity().getName());
        return outputJson.toString();
    }
    @POST
    @Path("/node/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteNode(@PathParam("id") String id) {
        NodeView nodeView = graphEditorBean.deleteNode(id);
        if(nodeView==null)
            return graphEditorBean.errorResponse();
        return graphEditorBean.successResponse();
    }
    @POST
    @Path("/transition/post")
    @Produces(MediaType.APPLICATION_JSON)
    public String postTransition(String data) {
        return graphEditorBean.addTransition(data).getJsonOfView().toString();
    }
    @GET
    @Path("/transition/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTransitionById(@PathParam("id") String id) {
        JSONObject resultJson = new JSONObject();
        TransitionView transitionView = GraphUtils.getTransitionViewByViewId(graphEditorBean.getViewGraph(), id);
        resultJson.put("transition", transitionView.getJsonOfEntity());
        JSONArray nodesJson = new JSONArray();
        Set<NodeView> nodeViews = graphEditorBean.getAllNodes();
        for(NodeView nodeView : nodeViews) {
            JSONObject nodeViewJson = nodeView.getJsonOfEntity();
            nodesJson.put(nodeViewJson);
        }
        resultJson.put("nodes", nodesJson);
        return resultJson.toString();
    }
    @POST
    @Path("/transition/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTransition(@PathParam("id") String viewId, String data) {
        TransitionView transitionView = graphEditorBean.editTransition(data,viewId);
        return transitionView.getJsonOfView().toString();
    }
    @POST
    @Path("/transition/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteTransition(@PathParam("id") String id) {
        graphEditorBean.deleteTransition(id);
        return graphEditorBean.successResponse();
    }
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public String save(String data) {
        return graphEditorBean.save(new JSONArray(data));
    }

    @POST
    @Path("/undo")
    @Produces(MediaType.APPLICATION_JSON)
    public String undo() {
        return graphEditorBean.undo();
    }
}
