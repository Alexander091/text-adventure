package rest;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.TransitionDAO;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by dimko_000 on 05.02.2016.
 */
@Path("/transition")
public class TransitionRestController {
    @EJB
    TransitionDAO transitionDAO;
    @EJB
    NodeDAO nodeDAO;
    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String transitionData(@PathParam("id") Long id) {
        Transition transition = transitionDAO.getById(id);
        JSONObject transitionJson = new JSONObject();
        transitionJson.put("id", transition.getId());
        transitionJson.put("name", transition.getName());
        transitionJson.put("sourceId", transition.getNodeByFromNode().getId());
        transitionJson.put("targetId", transition.getNodeByToNode().getId());
        return transitionJson.toString();
    }

    @POST
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    public String postNode(String data) {
        JSONObject jsonData = new JSONObject(data);
        Transition transition = new Transition();
        transition.setName(jsonData.getString("name"));
        transition.setNodeByFromNode(nodeDAO.getById(jsonData.getLong("source")));
        transition.setNodeByToNode(nodeDAO.getById(jsonData.getLong("target")));
        transition.setCondition("test condition");
        transitionDAO.saveOrUpdate(transition);
        JSONObject outputJson = new JSONObject();
        outputJson.put("group", "edges");
        JSONObject dataJson = new JSONObject();
        dataJson.put("id", "e"+transition.getId());
        dataJson.put("source","n"+ transition.getNodeByFromNode().getId());
        dataJson.put("target","n"+ transition.getNodeByToNode().getId());
        outputJson.put("data", dataJson);
        return outputJson.toString();
    }
    @POST
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTransition(@PathParam("id") Long id, String data) {
        JSONObject transitionJson = new JSONObject(data);
        Transition transition = transitionDAO.getById(id);
        transitionDAO.delete(transition); //removed elements don't delete in cytoscape, so i can't add elements with same id
        transition = new Transition();
        transition.setName(transitionJson.getString("name"));
        transition.setNodeByFromNode(nodeDAO.getById(transitionJson.getLong("source")));
        transition.setNodeByToNode(nodeDAO.getById(transitionJson.getLong("target")));
        transition.setCondition("test condition");
        transitionDAO.saveOrUpdate(transition);
        JSONObject outputJson = new JSONObject();
        outputJson.put("group", "edges");
        JSONObject dataJson = new JSONObject();
        dataJson.put("id", "e"+transition.getId());
        dataJson.put("source","n"+ transition.getNodeByFromNode().getId());
        dataJson.put("target","n"+ transition.getNodeByToNode().getId());
        outputJson.put("data", dataJson);
        return outputJson.toString();
    }
    @POST
    @Path("/delete/{id}")
    public void deleteTransition(@PathParam("id") Long id) {
        Transition transition = transitionDAO.getById(id);
        transitionDAO.delete(transition);
    }
}
