package com.storyshell.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.storyshell.model.CommentDetail;
import com.storyshell.model.FreindModel;
import com.storyshell.model.MediaList;
import com.storyshell.model.Message;
import com.storyshell.model.Post;

@Repository
public class ContentDataImpl implements ContentData {

	@Override
	public List<MediaList> getAllImages(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaList getProfileImage(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MediaList> getAllOtherMedia(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveMedia(MediaList media, int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveComment(CommentDetail comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CommentDetail> getAllComments(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int savePost(Post post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveNetwork(FreindModel friendModel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveMessage(Message message) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> getAllMessage(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getPToPMessage(int userId, int frinedId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAllPost(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAllChannelPost(String pageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreindModel> getAllFriendList(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreindModel> getAllFollowers(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
