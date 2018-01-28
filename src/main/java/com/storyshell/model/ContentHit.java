package com.storyshell.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Monarchpedo
 */
@Document(indexName = "hit", type = "hit", shards = 1, replicas = 0, refreshInterval = "-1")
public class ContentHit implements Serializable {

	private static final long serialVersionUID = 1665887545948658427L;

	@Id
	private int id;
	@NotNull
	private int userId;
	@NotNull
	private int postId;
	@NotNull
	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("ContentHit [").append("id=").append(id).append(",userId=").append(userId).append(",postId=")
				.append(postId).append(",type=").append(type);
		return result.toString();
	}

}
