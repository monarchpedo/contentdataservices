package com.storyshell.model;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
	private int userId;
	private List<CommentDetail> commentDetails;
	private int totalComments;
	private int totalLikes;
	private List<MediaList> mediaList;
	private int postStatus;
	private String createdDate;
	private String modifiedDate;
	private String title;
	private int postId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<CommentDetail> getCommentDetails() {
		return commentDetails;
	}

	public void setCommentDetails(List<CommentDetail> commentDetails) {
		this.commentDetails = commentDetails;
	}

	public int getTotalComments() {
		return totalComments;
	}

	public void setTotalComments(int totalComments) {
		this.totalComments = totalComments;
	}

	public int getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}

	public List<MediaList> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<MediaList> mediaList) {
		this.mediaList = mediaList;
	}

	public int getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(int postStatus) {
		this.postStatus = postStatus;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
}
