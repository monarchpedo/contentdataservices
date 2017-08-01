package com.storyshell.model;

import java.io.Serializable;

public class CommentDetail implements Serializable {
	/**
	 * @author RajaBose
	 * @category model class
	 */
	private static final long serialVersionUID = -3991575378651456738L;
	private int rowId;
	private int postId;
	private int userId;
	private String commentDetails;
	private int childLevel;
	private int parentId;
	private String modified_date;
	private String created_date;
	private int active;

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getCommentDetails() {
		return commentDetails;
	}

	public void setCommentDetails(String commentDetails) {
		this.commentDetails = commentDetails;
	}

	public int getChildLevel() {
		return childLevel;
	}

	public void setChildLevel(int childLevel) {
		this.childLevel = childLevel;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
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

}
