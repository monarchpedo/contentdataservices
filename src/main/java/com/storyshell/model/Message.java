package com.storyshell.model;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7090872080481235177L;
	private int msgId;
	private int userId;
	private int receiptId;
	private String message;
	private MediaList mediaList;
	private String createdDate;
	private int isActive;
	private int mediaId;
	private int groupId;

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MediaList getMediaList() {
		return mediaList;
	}

	public void setMediaList(MediaList mediaList) {
		this.mediaList = mediaList;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int active) {
		this.isActive = active;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
