package com.storyshell.controller;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.storyshell.model.CommentDetail;
import com.storyshell.model.ContentHit;
import com.storyshell.model.FreindModel;
import com.storyshell.services.CommentService;

/**
 * @author MonarchPedo
 */
@Controller
public class CommentController {

	@Inject
	private CommentService commentService;
	
	@Inject
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/push-comment")
	@SendTo("/client/notifications")
	public Response saveAndPushComment(CommentDetail commentDetail){
	    
		Response response = commentService.saveComment(commentDetail);
		return response;
	}
	
	
	@MessageMapping("/push-like-unlike")
	@SendTo("/client/hit")
	public Response saveAndPushLikeorUnlike(ContentHit contentHit){
		return null;
	}
	
	
	@MessageMapping("/follow")
	public void saveAndPushFollow(FreindModel friendModel){
		//fiendModel object saving to database
		messagingTemplate.convertAndSendToUser(String.valueOf(friendModel.getFriendId()), "/follow/push", friendModel);
	}
}
