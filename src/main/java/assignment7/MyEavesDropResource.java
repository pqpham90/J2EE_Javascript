package assignment7;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Map;

@Path("/")
public class MyEavesDropResource {
	
	public MyEavesDropResource() {
		
	}
	
	@GET
	@Path("/helloworld")
	@Produces("text/html")
	public String helloWorld() {
		System.out.println("Inside helloworld");
		return "Hello world ";
	}

	@GET
	@Path("/myeavesdrop")
	@Produces("application/xml")
	public String getData() {
		MyEavesDropService reader = new MyEavesDropServiceImpl();
		List<String> meetings = reader.getMeetings();
		Map<String, List<String>> allMeetings =  reader.getYears(meetings);

		String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		result += "<meetings>";

		for (String project: allMeetings.keySet()) {
			result += "<meeting>";
			int count = 0;

			for (String year: allMeetings.get(project)) {
				count += reader.getLog(project, year);
			}
			result += ("<name>" + project.replace("/", "") + "</name>");
			result += ("<count>" + count + "</count>");
			result += "</meeting>";
		}

		result += "</meetings>";

		return result;
	}
}
