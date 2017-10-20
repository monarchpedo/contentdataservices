package com.storyshell.dao;

import java.util.List;
import java.util.Map;

import com.storyshell.model.CommentDetail;
import com.storyshell.model.ContentHit;
import com.storyshell.model.FreindModel;
import com.storyshell.model.GroupNetwork;
import com.storyshell.model.MediaList;
import com.storyshell.model.Message;
import com.storyshell.model.Post;

public interface ContentData {
	public List<MediaList> getAllImages(int userId, int mediaType);

	public MediaList getProfileImage(int userId);

	public List<MediaList> getPostMedia(int postId);

	public Map<String, List<MediaList>> getAllOtherMedia(int userId);

	public int saveMedia(MediaList media);

	public int saveComment(CommentDetail comment);

	public int updateComment(CommentDetail comment, int commentId);

	public int deleteComment(int commentId, int userId);

	public int deleteCommentByOwner(int commentId, int postId); // when owner of
																// the post
																// deleted any
																// comment in
																// his/her post

	public List<CommentDetail> getAllComments(int postId);

	public List<CommentDetail> getComments(int parentId);

	public int savePost(Post post);

	public int updatePost(int postId, Post post);

	// to get post list on the basis of interest order by createdDate
	public List<Post> getPostList(List<String> interest, int offset);

	public List<Post> getPostByInterest(String interestValue, int offset);

	public List<Post> getAllPost(int userId);

	public List<Post> getAllChannelPost(String pageId);

	public int saveLikesHit(ContentHit hit);

	public int saveUnLikesHit(ContentHit hit);

	public List<ContentHit> getLikesHit(int postId, int type);

	public List<ContentHit> getUnLikesHit(int postId, int type);

	public int deleteLikesHit(int postId, int userId, int type);

	public int deleteUnLikesHit(int postId, int userId, int type);

	//List<MediaList> getAllImages(int userId, int mediaType);
	
	public int saveNetwork(FreindModel friendModel);
	
	public int createGroup(GroupNetwork grpNetwork);

	public int saveMessage(Message message);

	public List<Message> getMessage(int userId, int offset);//problem part
	
	public List<Message> getGroupMessage(int groupId, int offset);

	public List<Message> getPToPMessage(int userId, int frinedId, int offset);

	public List<FreindModel> getAllFriendList(int userId);//give the list of person with whom he or she can chat

	public List<FreindModel> getAllFollowers(int userId);
	
	public List<GroupNetwork> getAllGroup(int userId);
	
	
}
