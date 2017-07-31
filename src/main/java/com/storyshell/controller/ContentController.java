package com.storyshell.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.storyshell.services.ContentService;

@Component
@Path("/content")
public class ContentController {

	private static final String URL = "http://localhost:8080/";

	@Inject
	private ContentService contentService;

	private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

	@POST
	@Path("/media/{userId}")
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public String getMediaList(String body, @PathParam("userId") int userId, @QueryParam("name") String name,
			@HeaderParam("X-Auth-Token") String token, @Context HttpServletRequest request) {

		// FreindModel freindModel = new FreindModel();
		// return Response.status(200).entity(freindModel).build();
		String response;
		response = "id = \n";
		response += "body = " + body + "\n";
		response += "name = " + name + "\n";
		response += "token = " + token + "\n";
		response += "ip = " + request.getRemoteAddr() + "\n";
		return response;
	}

	@GET
	@Path("/media/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("image/jpg")
	public Response getMedia(@PathParam("userId") int userId, @HeaderParam("X-Auth-Token") String token) {
		String path = "C:/Users/MonarchPedo/Downloads/rajakishore.jpg";
		String anotherFile = "C:/Users/MonarchPedo/Downloads/rajakishore2.jpg";
		File file1 = new File(path);
		List<Object> listOfFile = new ArrayList<Object>();
		listOfFile.add((Object) file1);
		File file2 = new File(anotherFile);
		listOfFile.add((Object) file2);
		Map<String, Object> mapOfFile = new HashMap<String, Object>();
		mapOfFile.put("rajakishore.jpg", (Object) file1);
		mapOfFile.put("rajakishore2.jpg", (Object) file2);
		ResponseBuilder response = Response.ok(new Array({(Object) file1,(Object)file2));
		response.header("Content-Disposition", "attachment; filename={\"rajakishore.jpg\",\"rajakishore2.jpg\"}");
		// return Response.status(200).entity(mapOfFile).build();
		return response.build();
	}
		
		
	
	

}
