package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dimko_000 on 04.02.2016.
 */
@Path("/quest")
public class QuestRestController {
    @GET
    @Path("/getData")
    @Produces(MediaType.APPLICATION_JSON)
    public String testData() {
        return "[{\"group\": \"nodes\", \"data\": {\"id\": \"n0\"}, \"position\": { \"x\": 100, \"y\": 100}}, " +
                "{\"group\": \"nodes\", \"data\": {\"id\": \"n1\"}, \"position\": { \"x\": 200, \"y\": 200}}, " +
                "{\"group\": \"edges\", \"data\": {\"id\": \"e0\", \"source\": \"n0\", \"target\": \"n1\"}}]";
    }
}
