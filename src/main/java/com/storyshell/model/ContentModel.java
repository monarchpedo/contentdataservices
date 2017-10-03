package com.storyshell.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentModel implements Serializable {
	/**
	 * @author Raja Bose
	 * @category Model software developer
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

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(" ContentModel [ post=").append(convertToString(post)).append(", message=")
				.append(convertToString(message)).append(", mediaList=").append(convertToString(mediaList))
				.append(", freindModel=").append(convertToString(freindModel));

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
