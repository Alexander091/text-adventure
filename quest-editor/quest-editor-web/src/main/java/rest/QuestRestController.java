package rest;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.dao.TransitionDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Transition;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
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
    @EJB
    NodeDAO nodeDAO;
    @EJB
    TransitionDAO transitionDAO;
    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(@PathParam("id") Long id) {
        List<Node> nodes = nodeDAO.getNodesByQuestId(id);
        JSONArray resultJsonArray = new JSONArray();
        for(Node node : nodes){
            JSONObject nodeJson = new JSONObject();
            nodeJson.put("group", "nodes");
            JSONObject dataJson = new JSONObject();
            dataJson.put("id", "n"+node.getId());
            dataJson.put("name", node.getName());
            nodeJson.put("data", dataJson);
            String position = node.getPosition();
            Integer posx = Integer.parseInt(position.split(" ")[0]);
            Integer posy = Integer.parseInt(position.split(" ")[1]);
            JSONObject positionJson = new JSONObject();
            positionJson.put("x", posx);
            positionJson.put("y", posy);
            nodeJson.put("position", positionJson);
            resultJsonArray.put(nodeJson);
        }
        for (Node node : nodes) {
            List<Transition> transitions = node.getTransitions();
            for(Transition transition : transitions)
            {
                JSONObject transitionJson = new JSONObject();
                transitionJson.put("group", "edges");
                JSONObject dataJson = new JSONObject();
                dataJson.put("id", "e"+transition.getId());
                dataJson.put("source","n"+ transition.getNodeByFromNode().getId());
                dataJson.put("target","n"+ transition.getNodeByToNode().getId());
                transitionJson.put("data", dataJson);
                resultJsonArray.put(transitionJson);
            }
        }
        String result = resultJsonArray.toString();
        return result;/*
        return "[{\"group\": \"nodes\", \"data\": {\"id\": \"n0\", \"name\": \"a\"}, \"position\": { \"x\": 100, \"y\": 100}}, " +
                "{\"group\": \"nodes\", \"data\": {\"id\": \"n1\", \"name\": \"b\"}, \"position\": { \"x\": 200, \"y\": 200}}, " +
                "{\"group\": \"nodes\", \"data\": {\"id\": \"n2\", \"name\": \"c\"}, \"position\": { \"x\": 300, \"y\": 300}}, " +
                "{\"group\": \"edges\", \"data\": {\"id\": \"e0\", \"source\": \"n0\", \"target\": \"n1\"}}," +
                "{\"group\": \"edges\", \"data\": {\"id\": \"e1\", \"source\": \"n1\", \"target\": \"n2\"}}," +
                "{\"group\": \"edges\", \"data\": {\"id\": \"e2\", \"source\": \"n1\", \"target\": \"n0\"}}," +
                "{\"group\": \"edges\", \"data\": {\"id\": \"e3\", \"source\": \"n2\", \"target\": \"n1\"}}," +
                "{\"group\": \"edges\", \"data\": {\"id\": \"e4\", \"source\": \"n0\", \"target\": \"n2\"}}," +
                "{\"group\": \"edges\", \"data\": {\"id\": \"e5\", \"source\": \"n2\", \"target\": \"n0\"}}]";*/
    }
}
