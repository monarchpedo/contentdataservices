package com.storyshell.services;

import java.util.List;

import javax.ws.rs.core.Response;

import com.storyshell.model.Post;

public interface ContentService {
	//public int saveComment(CommentDetail comment);

	//public int saveMessage(Message message);

	//public int saveFriend(FreindModel friendModel);

	public Response savePost(Post post);

	//public int deleteMessage(int messageId);

	public int deletePost(int postId);

	//public int deleteComment(int commentId);

	//public int unFreind(int userId, int friendId);

	//public int removeComment(int postId, int removedCommentId);

	//public int deleteMedia(int imageId);

	//public List<MediaList> getMediaList(int userId);

	//public MediaList getProfileMedia(int userId);

	//public List<Message> getMessage(int userId);

	//public List<Message> getMessage(int userId, int friendId);

	public List<Post> getPostByUserId(int userId);

	public List<Post> getPostByPageId(int pageId);

	//public List<CommentDetail> getComment(int postId);

	//public CommentDetail getSingleComment(int postId, int commentLevel, int userId);

	//public List<FreindModel> getFriendList(int userId);

	//public List<Message> getMessageByGroupId(int groupId);

	public List<Post> getPostBySection(int sectionId, int userId);

	public List<Post> getPostByChannelSection(int sectionId, int pageId, String channelName);

	//public List<FreindModel> getFolloweOfPage(String channelName, int pageId);

	// public int getNumberOfFolloweOFChannel(String channelName,int pageId);

}
