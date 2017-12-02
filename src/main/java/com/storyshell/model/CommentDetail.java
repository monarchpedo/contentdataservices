package com.storyshell.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class CommentDetail implements Serializable {
	/**
	 * @author RajaBose
	 * @category model class
	 */
	private static final long serialVersionUID = -3991575378651456738L;
	private int id;
	private int postId;
	private int userId;
	private String comment;
	private int parentId;
	private Date modifiedDate;
	private Date createdDate;
	private int active;
	private int favLikes;
	private int unFavLikes;

	public int getId() {
		return id;
	}

	public void setId(int rowId) {
		this.id = rowId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String commentDetails) {
		this.comment = commentDetails;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modified_date) {
		this.modifiedDate = modified_date;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date created_date) {
		this.createdDate = created_date;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("CommentDetail [").append("rowId=").append(id).append(", userId=").append(userId)
				.append(", postId=").append(postId).append(", commentDetails=").append(comment).append(", parentId")
				.append(parentId).append(", modified_date=").append(modifiedDate).append(", created_date=")
				.append(createdDate).append(", active=").append(active).append(", favLikes= ").append(favLikes)
				.append(", unFavLikes=").append(unFavLikes);
		return result.toString();
	}

	public <T> String convertToString(List<T> list) {
		StringBuilder builder = new StringBuilder();
		for (T item : list) {
			builder.append(item.toString());
			builder.append("\t");
		}
		return builder.toString();

	}

}
