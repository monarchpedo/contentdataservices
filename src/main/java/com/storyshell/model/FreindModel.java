package com.storyshell.model;

import java.io.Serializable;
import java.util.List;

public class FreindModel implements Serializable {
	/**
	 * @author RajaBose
	 * @category model class
	 */
	private static final long serialVersionUID = 6104875073573465025L;
	private int rowId;
	private int friendId;
	private int userId;
	private String username;
	private int followingStatus;
	private String joinedDate;
	private int active;

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public int getFollowingStatus() {
		return followingStatus;
	}

	public void setFollowingStatus(int followingStatus) {
		this.followingStatus = followingStatus;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		result.append(" FreindModel [").append(", rowId=").append(rowId).append(", friendId=").append(friendId)
				.append(", userId=").append(userId).append(", username=").append(username).append(", followingStatus=")
				.append(followingStatus).append(", joinedDate=").append(joinedDate).append(", active=").append(active);
		return result.toString();
	}
	
	private <T> String convertToString(List<T> list) {
		StringBuilder builder = new StringBuilder();
		for (T item : list) {
			builder.append(item.toString());
			builder.append("\t,");
		}
		return builder.toString();
	}

}
