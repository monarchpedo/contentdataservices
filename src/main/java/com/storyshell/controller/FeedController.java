package com.storyshell.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.storyshell.model.Post;
import com.storyshell.services.ContentService;

/**
 * @author Monarchpedo
 */

@Component
@Path("/user/content")
public class FeedController {

	private static Logger LOG = LoggerFactory.getLogger(FeedController.class);
	@Autowired
	private ContentService contentService;

	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPost(final Post post) {
		return null;
		// return contentService.savePost(post);
	}

	@POST
	@Path("/post")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	public Response addPostWithMedia(final FormDataMultiPart multiPart) {
		List<FormDataBodyPart> bodyParts = multiPart.getFields("files");
		Post post = getAndSetPost(multiPart);
		return contentService.savePost(post, bodyParts);
	}

	private Post getAndSetPost(final FormDataMultiPart multiPart) {
		Post post = new Post();
		if (multiPart.getField("userId") != null) {
			System.out.println((multiPart.getField("userId").getValue().trim()));
			post.setUserId(Integer.valueOf(multiPart.getField("userId").getValue().trim()));
		}
		if (multiPart.getField("postSectionId") != null) {
			post.setPostSectionId(multiPart.getField("postSectionId").toString().trim());
		}
		if (multiPart.getField("isMediaContain") != null) {
			post.setIsMediaContain(Integer.valueOf(multiPart.getField("isMediaContain").getValue().trim()));
		}
		if (multiPart.getField("isChannelPost") != null) {
			post.setIsChannelPost(Integer.valueOf(multiPart.getField("isChannelPost").getValue().trim()));
		}
		if (multiPart.getField("postType") != null) {
			post.setPostType(Integer.valueOf(multiPart.getField("postType").getValue().trim()));
		}
		if (multiPart.getField("title") != null) {
			post.setTitle(multiPart.getField("title").getValue().trim());
		}
		if (multiPart.getField("postContent") != null) {
			post.setPostContent(multiPart.getField("postContent").getValue().trim());
		}
		if (multiPart.getField("totalComment") != null) {
			post.setTotalComment(Integer.valueOf(multiPart.getField("totalComment").getValue().trim()));
		}
		if (multiPart.getField("favLikes") != null) {
			post.setFavLikes(Integer.valueOf(multiPart.getField("favLikes").getValue().trim()));
		}
		if (multiPart.getField("unFavLikes") != null) {
			post.setUnFavLikes(Integer.valueOf(multiPart.getField("unFavLikes").getValue().trim()));
		}
		if (multiPart.getField("postStatus") != null) {
			post.setPostStatus(Integer.valueOf(multiPart.getField("postStatus").getValue().trim()));
		}
		return post;
	}

}
