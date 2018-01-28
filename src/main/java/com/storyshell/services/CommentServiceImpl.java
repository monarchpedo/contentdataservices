package com.storyshell.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.apache.catalina.connector.ResponseFacade;
import org.springframework.transaction.annotation.Transactional;

import com.storyshell.contentdataservices.GenericExceptionHandler;
import com.storyshell.dao.ContentData;
import com.storyshell.model.CommentDetail;
import com.storyshell.util.ResponseGenerator;

public class CommentServiceImpl implements CommentService {

	@Inject
	private ContentData contentData;

	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response saveComment(CommentDetail commentDetail) {
		long resuldId = contentData.saveComment(commentDetail);
		if (resuldId != 0) {
			return ResponseGenerator.generateResponse("comment has been saved", Response.Status.CREATED);
		}
		return ResponseGenerator.generateResponse("comment has not been saved", Response.Status.BAD_REQUEST);
	}

	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response updateComment(int id, CommentDetail commentDetail) {
		int result = contentData.updateComment(commentDetail, id);
		if (result != 0) {
			return ResponseGenerator.generateResponse("commnet has been updated", Response.Status.CREATED);
		}
		return ResponseGenerator.generateResponse(commentDetail, Response.Status.BAD_REQUEST);
	}

	@Override
	public Response deleteComment(int commentId, int userId) {
		int result = contentData.deleteComment(commentId, userId);
		if (result != 0) {
			return ResponseGenerator.generateResponse("comment has been deleted", Response.Status.ACCEPTED);
		}

		return ResponseGenerator.generateResponse("Error in deleting comment", Response.Status.BAD_REQUEST);
	}

	@Override
	public Response getComment(int postId, int offset) {
		List<CommentDetail> commentList = null;
		commentList = contentData.getAllComments(postId, offset);
		return ResponseGenerator.generateResponse(commentList, Response.Status.ACCEPTED);
	}

}
