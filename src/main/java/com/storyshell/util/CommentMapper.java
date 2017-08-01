package com.storyshell.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import com.storyshell.model.CommentDetail;

public class CommentMapper implements RowMapper<CommentDetail> {

	@Override
	public CommentDetail mapRow(ResultSet rs, int line) throws SQLException {
		// TODO Auto-generated method stub
		CommentDetail comment = new CommentDetail();
		if (rs.getInt("id") != 0) {
			comment.setRowId(rs.getInt("id"));
		}

		if (rs.getInt("userId") != 0) {
			comment.setUserId(rs.getInt("userId"));
		}

		if (rs.getInt("postId") != 0) {
			comment.setPostId(rs.getInt("postId"));
		}

		if (!StringUtils.isEmpty(rs.getString("commentDetails"))) {
			comment.setCommentDetails(rs.getString("commentDetails"));
		}

		if (rs.getInt("parentId") != 0) {
			comment.setParentId(rs.getInt("parentId"));
		}

		if (!StringUtils.isEmpty(rs.getString("createdDate"))) {
			comment.setCreated_date(rs.getString("createdDate"));
		}

		if (rs.getInt("active") != 0) {
			comment.setActive(rs.getInt("active"));
		}

		return comment;
	}

}
