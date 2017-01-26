package bobsalesman.restservices;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bobsalesman.algorithms.ESolversAlgorithm;

@Path("/")
public class Bob {
	
	BobsFunctions bobsFunctions;
	BobsMarshall bobsMarshall;
	
	public Bob(){
		this.bobsFunctions = new BobsFunctions();
		this.bobsMarshall = new BobsMarshall();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/requestRoute")
	public Response requestBestRoute(String rawData) throws IOException {
		int id = bobsFunctions.requestBestRoute(rawData, ESolversAlgorithm.GLOBAL_GREEDY);
		return Response.ok("{ \"executionID\": " + id + " }", MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/download")
	public Response getBestRoute(@QueryParam("key") int key) {
		String rawData = bobsFunctions.getBestRoute(key);
		return Response.ok(rawData, MediaType.TEXT_PLAIN).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getProgress")
	public Response getProgress(@QueryParam("key") int key) {
		double progress = bobsFunctions.getProgress(key);
		return Response.ok("{ \"progress\": " + progress + " }", MediaType.APPLICATION_JSON).build();
	}
	
}
