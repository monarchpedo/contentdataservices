package com.storyshell.model;

import java.io.Serializable;
import java.util.List;

public class ContentModel implements Serializable {
	/**
	 * @author Raja Bose
	 * @category Model
	 * software developer
	 */
	private static final long serialVersionUID = -3686108793354094929L;
	private List<Post> post;
	private List<Message> message;
	private List<MediaList> mediaList;
	private List<FreindModel> freindModel;

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

	public List<MediaList> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<MediaList> mediaList) {
		this.mediaList = mediaList;
	}

	public List<FreindModel> getFreindModel() {
		return freindModel;
	}

	public void setFreindModel(List<FreindModel> freindModel) {
		this.freindModel = freindModel;
	}

}
