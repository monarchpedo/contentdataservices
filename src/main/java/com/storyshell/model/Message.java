package com.storyshell.model;

import java.io.Serializable;

public class Message implements Serializable {
	private int msgId;
	private int userId;
	private int receiptUserId;
	private String message;
	private MediaList mediaList;
	private String createdDate;
	private int active;

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

	public int getReceiptUserId() {
		return receiptUserId;
	}

	public void setReceiptUserId(int receiptUserId) {
		this.receiptUserId = receiptUserId;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}
