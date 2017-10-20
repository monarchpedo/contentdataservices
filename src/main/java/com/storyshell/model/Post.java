package com.storyshell.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author Monarchpedo
 *
 */
public class Post implements Serializable {
	
	private static final long serialVersionUID = -7381056071294028885L;
	private int id;
	@NotNull
	private int userId;
	private List<CommentDetail> commentDetails;
	@NotNull
	private int totalComment;
	@NotNull
	private int favLikes;
	@NotNull
	private int unFavLikes;
	private List<MediaList> mediaList;
	@NotNull
	private int postStatus;
	private Date createdDate;
	private Date modifiedDate;
	@NotNull
	private String title;
	@NotNull
	private int postSectionId;
	@NotNull
	private int isMediaContain;
	private String pageId;
	@NotNull
	private int isChannelPost;
	@NotNull
	private int postType;
	@NotNull
	private String postContent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(int totalComment) {
		this.totalComment = totalComment;
	}

	public int getFavLikes() {
		return favLikes;
	}

	public void setFavLikes(int favLikes) {
		this.favLikes = favLikes;
	}

	public int getUnFavLikes() {
		return unFavLikes;
	}

	public void setUnFavLikes(int unFavLikes) {
		this.unFavLikes = unFavLikes;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPostSectionId() {
		return postSectionId;
	}

	public void setPostSectionId(int postSectionId) {
		this.postSectionId = postSectionId;
	}

	public int getIsMediaContain() {
		return isMediaContain;
	}

	public void setIsMediaContain(int isMediaContain) {
		this.isMediaContain = isMediaContain;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public int getIsChannelPost() {
		return isChannelPost;
	}

	public void setIsChannelPost(int isChannelPost) {
		this.isChannelPost = isChannelPost;
	}

	public int getPostType() {
		return postType;
	}

	public void setPostType(int postType) {
		this.postType = postType;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(" Post [").append("id =").append(id).append(", userId=").append(userId)
				.append(", commentDetails=").append(convertToString(commentDetails)).append(", totalComment=")
				.append(totalComment).append(", favLikes=").append(favLikes).append(", unFavLikes=").append(unFavLikes)
				.append(", mediaList=").append(convertToString(mediaList)).append(", postStatus=").append(postStatus)
				.append(", createdDate=").append(createdDate).append(", modifiedDate=").append(modifiedDate)
				.append(", title=").append(title).append(", postSectionId=").append(postSectionId)
				.append(", isMediaContain=").append(isMediaContain).append(", pageId=").append(pageId)
				.append(", isChannelPost=").append(isChannelPost).append(", postType=").append(postType)
				.append(", postContent=").append(postContent);
		return result.toString();
	}

	private <T> String convertToString(List<T> list) {
		StringBuilder builder = new StringBuilder();
		for (T item : list) {
			builder.append(item.toString());
			builder.append("\t");
		}
		return builder.toString();

	}
}
