package com.storyshell.model;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotNull;

/**
 * @author Monarchpedo
 * @category model class
 */
public class GroupNetwork implements Serializable {

	private static final long serialVersionUID = 8840848353914218787L;

	private int id;
	@NotNull
	private int groupId; // id of group is generated when someone start creating group on post group button 
	@NotNull
	private String groupName;
	@NotNull
	private int userId;
	@NotNull
	private int active;
	@NotNull
	private Date createdDate;
	@NotNull
	private int groupActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getGroupActive() {
		return groupActive;
	}

	public void setGroupActive(int groupActive) {
		this.groupActive = groupActive;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("GroupNetwork [").append("id=").append(id).append(",groupId=").append(groupId)
				.append(",groupName=").append(groupName).append(",userId=").append(userId).append(",active=")
				.append(active).append(",createdDate=").append(createdDate).append(",groupActive=").append(groupActive);
		return result.toString();
	}

}
