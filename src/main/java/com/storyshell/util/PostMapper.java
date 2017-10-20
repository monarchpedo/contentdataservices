package com.storyshell.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import com.storyshell.model.Post;

public class PostMapper implements RowMapper<Post> {

	@Override
	public Post mapRow(ResultSet rs, int line) throws SQLException {
		// TODO Auto-generated method stub
		Post post = new Post();

		if (rs.getInt("id") != 0) {
			post.setUserId(rs.getInt("id"));
		}

		if (rs.getInt("userId") != 0) {
			post.setUserId(rs.getInt("userId"));
		}

		if (rs.getInt("id") != 0) {
			post.setId(rs.getInt("id"));
		}

		if (rs.getInt("favLikes") != 0) {
			post.setFavLikes(rs.getInt("favLikes"));
		}

		if (rs.getInt("unFavLikes") != 0) {
			post.setUnFavLikes(rs.getInt("unFavLikes"));
		}

		if (rs.getInt("totalComment") != 0) {
			post.setTotalComment(rs.getInt("totalComment"));
		}

		if (rs.getInt("postType") != 0) {
			post.setPostType(rs.getInt("postType"));
		}

		if (rs.getInt("isMediaContain") != 0) {
			post.setIsMediaContain(rs.getInt("isMediaContain"));
		}

		if (rs.getInt("isChannelPost") != 0) {
			post.setIsChannelPost(rs.getInt("isChannelPost"));
			;
		}

		if (rs.getInt("postSectionId") != 0) {
			post.setPostSectionId(rs.getInt("postSectionId"));
		}

		if (!StringUtils.isEmpty(rs.getString("title"))) {
			post.setTitle(rs.getString("title"));
		}

		if (!StringUtils.isEmpty(rs.getString("postCotent"))) {
			post.setPostContent(rs.getString("postContent"));
		}

		if (!StringUtils.isEmpty(rs.getString("createdDate"))) {
			post.setCreatedDate(rs.getDate("createdDate"));
		}

		if (!StringUtils.isEmpty(rs.getString("modifiedDate"))) {
			post.setModifiedDate(rs.getDate("modifiedDate"));
		}

		if (!StringUtils.isEmpty(rs.getString("pageId"))) {
			post.setPageId(rs.getString("pageId"));
		}

		return post;
	}

}
