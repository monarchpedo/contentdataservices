package com.storyshell.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author MonarchPedo
 * @category model class
 */

public class MediaList implements Serializable {
	private static final long serialVersionUID = -8274844870472897499L;
	private int id;
	private int postId;
	private int userId;
	private String mediaLink;
	private String location;
	private Date modifiedDate;
	private Date createdDate;
	private int isAvailabel;
	private String mediaType;

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getMediaLink() {
		return mediaLink;
	}

	public void setMediaLink(String imageId) {
		this.mediaLink = imageId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getIsAvailabel() {
		return isAvailabel;
	}

	public void setIsAvailabel(int active) {
		this.isAvailabel = active;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(" MediaList [").append(" rowId=").append(id).append(", postId=").append(postId)
				.append(", userId=").append(userId).append(", mediaId=").append(mediaLink).append(", location=")
				.append(location).append(", modifiedDate=").append(modifiedDate).append(", createdDate=")
				.append(createdDate).append(", active=").append(isAvailabel).append(", mediaType=").append(mediaType);
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
