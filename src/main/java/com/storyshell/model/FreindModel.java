package com.storyshell.model;

import java.io.Serializable;

public class FreindModel implements Serializable {
	int rowId;
	int friendId;
	String username;
	int followingStatus;
	String joinedDate;
	int active;

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

}
