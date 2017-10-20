package com.storyshell.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class FreindModel implements Serializable {
	/**
	 * @author RajaBose
	 * @category model class
	 */
	private static final long serialVersionUID = 6104875073573465025L;
	private int id;
	private int friendId; //just part of the 
	private int userId; //userId of user who are owner of this profile
	private String shortHandName;//this name is assigned by userId to show friendId name as this name
	private int isFollowing;
	private Date joinedDate;
	private int active;

	public int getId() {
		return id;
	}

	public void setId(int rowId) {
		this.id = rowId;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public int getIsFollowing() {
		return isFollowing;
	}

	public void setIsFollowing(int followingStatus) {
		this.isFollowing = followingStatus;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public String getShortHandName() {
		return shortHandName;
	}

	public void setShortHandName(String username) {
		this.shortHandName = username;
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
		result.append(" FreindModel [").append(", rowId=").append(id).append(", friendId=").append(friendId)
				.append(", userId=").append(userId).append(", username=").append(shortHandName).append(", followingStatus=")
				.append(isFollowing).append(", joinedDate=").append(joinedDate).append(", active=").append(active);
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
