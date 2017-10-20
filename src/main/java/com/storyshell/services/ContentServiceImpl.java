package com.storyshell.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.storyshell.contentdataservices.GenericExceptionHandler;
import com.storyshell.dao.ContentData;
import com.storyshell.model.Post;
import com.storyshell.util.GenericRestTemplateCall;
import com.storyshell.util.ResponseGenerator;

@Service
public class ContentServiceImpl implements ContentService {

	@Inject
	private ContentData contentData;
	private static final String AUTH_URL = "localhost:8000/oauth/v1/";

	@Override
	public Response savePost(Post post) throws GenericExceptionHandler {
		try {
			GenericRestTemplateCall<String, Boolean> restTemplate = new GenericRestTemplateCall<String, Boolean>();
			boolean isExists = (boolean) restTemplate.doGetExecute("/user/" + post.getUserId() + "/check", null,
					Boolean.class);
			if (isExists == false) {
				throw new GenericExceptionHandler("userId does not exists");
			}
			int saveResult = contentData.savePost(post);
			if (saveResult == 0) {
				return ResponseGenerator.generateResponse("Error in saving post,please try again...",
						Response.Status.CONFLICT);
			}
			return ResponseGenerator.generateResponse("post has been saved", Response.Status.CREATED);
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public int deletePost(int postId) {
		return 0;
	}

	@Override
	public List<Post> getPostByUserId(int userId) {
		return null;
	}

	@Override
	public List<Post> getPostByPageId(int pageId) {
		return null;
	}

	@Override
	public List<Post> getPostBySection(int sectionId, int userId) {
		return null;
	}

	@Override
	public List<Post> getPostByChannelSection(int sectionId, int pageId, String channelName) {
		return null;
	}

}
