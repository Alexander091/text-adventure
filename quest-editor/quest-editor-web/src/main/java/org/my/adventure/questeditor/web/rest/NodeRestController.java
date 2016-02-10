package org.my.adventure.questeditor.web.rest;

import jdk.nashorn.internal.parser.JSONParser;
import org.hibernate.Query;
import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.dao.TransitionDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.json.stream.JsonParser;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by dimko_000 on 05.02.2016.
 */
@Path("/node")
public class NodeRestController {
    @EJB
    NodeDAO nodeDAO;
    @EJB
    QuestDAO questDAO;
    @EJB
    TransitionDAO transitionDAO;
    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String nodeData(@PathParam("id") Long id) {
        JSONObject nodeJson = new JSONObject();
        Node node = nodeDAO.getById(id);
        nodeJson.put("id", node.getId());
        nodeJson.put("name", node.getName());
        nodeJson.put("description", node.getDescription());
        return nodeJson.toString();
    }

    @POST
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    public String postNode(String data) {
        JSONObject jsonData = new JSONObject(data);
        Node node = new Node();
        node.setName(jsonData.getString("name"));
        node.setDescription(jsonData.getString("description"));
        node.setQuestByQuestId(questDAO.getById(jsonData.getLong("questId")));
        node.setPosition(jsonData.getString("position"));
        nodeDAO.saveOrUpdate(node);
        JSONObject outputJson = new JSONObject();
        outputJson.put("group", "nodes");
        JSONObject dataJson = new JSONObject();
        dataJson.put("id", "n"+node.getId());
        dataJson.put("name", node.getName());
        outputJson.put("data", dataJson);
        String position = node.getPosition();
        Integer posx = Integer.parseInt(position.split(" ")[0]);
        Integer posy = Integer.parseInt(position.split(" ")[1]);
        JSONObject positionJson = new JSONObject();
        positionJson.put("x", posx);
        positionJson.put("y", posy);
        outputJson.put("position", positionJson);
        return outputJson.toString();
    }

    @POST
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateNode(@PathParam("id") Long id, String data) {
        JSONObject jsonData = new JSONObject(data);
        Node node = nodeDAO.getById(id);
        node.setName(jsonData.getString("name"));
        node.setDescription(jsonData.getString("description"));
        node.setQuestByQuestId(questDAO.getById(jsonData.getLong("questId")));
        node.setPosition(jsonData.getString("position"));
        nodeDAO.saveOrUpdate(node);
        JSONObject outputJson = new JSONObject();
        outputJson.put("name", node.getName());
        return outputJson.toString();
    }
    @POST
    @Path("/delete/{id}")
    public void deleteNode(@PathParam("id") Long id) {
        Node node = nodeDAO.getById(id);
       /* List<Transition> neighborTransitions = nodeDAO.getNeighborTransitions(id);
        for(Transition t : neighborTransitions) {
            transitionDAO.delete(t);
        }*/
        nodeDAO.delete(node);
    }
}
