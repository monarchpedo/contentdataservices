package com.storyshell.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import com.storyshell.model.MediaList;

public class MediaMapper implements RowMapper<MediaList> {

	@Override
	public MediaList mapRow(ResultSet rs, int line) throws SQLException {
		// TODO Auto-generated method stub
		MediaList mediaList = new MediaList();
		if (rs.getInt("Id") != 0) {
			mediaList.setRowId(rs.getInt("id"));
		}

		if (rs.getInt("userId") != 0) {
			mediaList.setUserId(rs.getInt("userId"));
		}

		if (rs.getInt("postId") != 0) {
			mediaList.setPostId(rs.getInt("postId"));
		}

		if (!StringUtils.isEmpty(rs.getString("mediaLink"))) {
			mediaList.setMediaId(rs.getString("mediaLink"));
		}

		if (!StringUtils.isEmpty(rs.getString("location"))) {
			mediaList.setLocation(rs.getString("location"));
		}

		if (!StringUtils.isEmpty(rs.getString("mediaType"))) {
			mediaList.setMediaType(rs.getString("mediaType"));
		}

		if (!StringUtils.isEmpty(rs.getString("modifiedDate"))) {
			mediaList.setModifiedDate(rs.getString("modifiedDate"));
		}

		if (!StringUtils.isEmpty(rs.getString("createdDate"))) {
			mediaList.setCreatedDate(rs.getString("createdDate"));
		}
		return mediaList;
	}

}
