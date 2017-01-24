package bobsalesman.restservices;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Bob {
	
	BobsFunctions bobsFunctions;
	BobsMarshall bobsMarshall;
	
	public Bob(){
		this.bobsFunctions = new BobsFunctions();
		this.bobsMarshall = new BobsMarshall();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/reqBestRoute")
	public Response requestBestRoute(String url) throws IOException {
		File file = bobsMarshall.getFile(url);
		bobsFunctions.requestBestRoute(file);
		return Response.ok("Funcionou", MediaType.TEXT_PLAIN).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getBestRoute")
	public Response getBestRoute(@QueryParam("key") int key) {
		String url = bobsFunctions.getBestRoute(key);
		return Response.ok(url, MediaType.TEXT_PLAIN).build();
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getProgress")
	public Response getProgress(@QueryParam("key") int key) {
		double progress = bobsFunctions.getProgress(key);
		return Response.ok(progress, MediaType.TEXT_PLAIN).build();
	}
}
