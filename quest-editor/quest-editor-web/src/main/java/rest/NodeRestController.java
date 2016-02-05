package rest;

import org.my.adventure.dao_manager.api.dao.NodeDAO;
import org.my.adventure.dao_manager.api.entities.Node;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dimko_000 on 05.02.2016.
 */
@Path("/node")
public class NodeRestController {
    @EJB
    NodeDAO nodeDAO;
    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String nodeData(@PathParam("id") Long id) {
        JSONObject nodeJson = new JSONObject();
        Node node = nodeDAO.getById(id);
        nodeJson.put("id", node.getId());
        nodeJson.put("name", node.getName());
        nodeJson.put("description", node.getDescription());

       /* nodeJson.put("group", "nodes");
        JSONObject dataJson = new JSONObject();
        dataJson.put("id", "n"+node.getId());
        dataJson.put("name", node.getName());
        dataJson.put("description", node.getDescription());
        nodeJson.put("data", dataJson);
        String position = node.getPosition();
        Integer posx = Integer.parseInt(position.split(" ")[0]);
        Integer posy = Integer.parseInt(position.split(" ")[1]);
        JSONObject positionJson = new JSONObject();
        positionJson.put("x", posx);
        positionJson.put("y", posy);
        nodeJson.put("position", positionJson);*/
        return nodeJson.toString();
    }
}
