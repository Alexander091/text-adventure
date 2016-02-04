package rest;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.my.adventure.dao_manager.api.entities.Quest;
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
    @GET
    @Path("/getData/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String testData(@PathParam("id") Long id) {
        List<Node> nodes = nodeDAO.getNodesByQuestId(id);
        JSONArray resultJsonArray = new JSONArray();
        for(Node node : nodes){
            JSONObject nodeJson = new JSONObject();
            nodeJson.put("group", "nodes");
            JSONObject dataJson = new JSONObject();
            dataJson.put("id", node.getId());
            dataJson.put("name", node.getName());
            dataJson.put("description", node.getDescription());
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
        String result = resultJsonArray.toString();
        return result;
//        return "[{\"group\": \"nodes\", \"data\": {\"id\": \"n0\"}, \"position\": { \"x\": 100, \"y\": 100}}, " +
//                "{\"group\": \"nodes\", \"data\": {\"id\": \"n1\"}, \"position\": { \"x\": 200, \"y\": 200}}, " +
//                "{\"group\": \"edges\", \"data\": {\"id\": \"e0\", \"source\": \"n0\", \"target\": \"n1\"}}]";
    }
}
