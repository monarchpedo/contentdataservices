package com.storyshell.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.storyshell.model.CommentDetail;
import com.storyshell.model.FreindModel;
import com.storyshell.model.MediaList;
import com.storyshell.model.Message;
import com.storyshell.model.Post;

@Service
public class ContentServiceImpl implements ContentService {

	@Override
	public int saveComment(CommentDetail comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveMessage(Message message) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveFriend(FreindModel friendModel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int savePost(Post post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMessage(int messageId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePost(int postId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteComment(int commentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int unFreind(int userId, int friendId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeComment(int postId, int removedCommentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMedia(int imageId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MediaList> getMediaList(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaList getProfileMedia(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getMessage(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getMessage(int userId, int friendId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByPageId(int pageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentDetail> getComment(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDetail getSingleComment(int postId, int commentLevel, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreindModel> getFriendList(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getMessageByGroupId(int groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostBySection(int sectionId, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByChannelSection(int sectionId, int pageId, String channelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreindModel> getFolloweOfPage(String channelName, int pageId) {
		// TODO Auto-generated method stub
		return null;
	}

}
