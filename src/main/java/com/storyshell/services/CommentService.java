package com.storyshell.services;

import java.util.List;

import javax.ws.rs.core.Response;

import com.storyshell.model.CommentDetail;

public interface CommentService {
	public Response saveComment(CommentDetail commentDetail);

	public Response updateComment(int id, CommentDetail commentDetail);

	public Response deleteComment(int commentId, int userId);

	public Response getComment(int postId, int offset);

}
