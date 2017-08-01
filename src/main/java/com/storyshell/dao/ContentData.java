package com.storyshell.dao;

import java.util.List;

import com.storyshell.model.CommentDetail;
import com.storyshell.model.FreindModel;
import com.storyshell.model.MediaList;
import com.storyshell.model.Message;
import com.storyshell.model.Post;

public interface ContentData {
	public List<MediaList> getAllImages(int userId, int mediaType);

	public MediaList getProfileImage(int userId);

	public List<MediaList> getAllOtherMedia(int userId);

	public int saveMedia(MediaList media, int userId);

	public int saveComment(CommentDetail comment);

	public List<CommentDetail> getAllComments(int postId);

	public int savePost(Post post);

	public int saveNetwork(FreindModel friendModel);

	public int saveMessage(Message message);

	public List<Message> getAllMessage(int userId);

	public List<Message> getPToPMessage(int userId, int frinedId);

	public List<Post> getAllPost(int userId);

	public List<Post> getAllChannelPost(String pageId);

	public List<FreindModel> getAllFriendList(int userId);

	public List<FreindModel> getAllFollowers(int userId);

	// List<MediaList> getAllImages(int userId, int mediaType);

}
