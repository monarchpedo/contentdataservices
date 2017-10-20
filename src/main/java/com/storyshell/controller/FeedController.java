package com.storyshell.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.storyshell.model.Post;
import com.storyshell.services.ContentService;
	
/**
 * @author Monarchpedo
 */

@Component
@Path("/user/content")
public class FeedController {

	@Autowired
	private ContentService contentService;

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPost(@Valid Post post) {
		return contentService.savePost(post);
	}
	
	
}
