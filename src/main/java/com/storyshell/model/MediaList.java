package com.storyshell.model;

import java.io.Serializable;

public class MediaList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274844870472897499L;
	private int rowId;
	private int postId;
	private int userId;
	private String mediaId;
	private String location;
	private String modifiedDate;
	private String createdDate;
	private int active;
	private String mediaType;

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

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

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String imageId) {
		this.mediaId = imageId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
