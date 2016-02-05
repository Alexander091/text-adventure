package rest;

import org.my.adventure.dao_manager.api.dao.TransitionDAO;
import org.my.adventure.dao_manager.api.entities.Transition;
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
@Path("/transition")
public class TransitionRestController {
    @EJB
    TransitionDAO transitionDAO;
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
        transitionJson.put("sourceName", transition.getNodeByFromNode().getName());
        transitionJson.put("targetName", transition.getNodeByToNode().getName());
        return transitionJson.toString();//l
    }
}
