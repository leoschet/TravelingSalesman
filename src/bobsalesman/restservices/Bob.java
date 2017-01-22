package bobsalesman.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Bob {
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/reqBestRoute")
	public Response requestBestRoute(String xml) {
		return Response.ok("Funcionou", MediaType.TEXT_PLAIN).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getBestRoute")
	public Response getBestRoute(@QueryParam("key") String key) {
		return Response.ok("Funcionou", MediaType.TEXT_PLAIN).build();
	}
}
