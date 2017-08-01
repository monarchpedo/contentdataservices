package com.storyshell.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import com.storyshell.model.FreindModel;

public class FriendMapper implements RowMapper<FreindModel> {

	@Override
	public FreindModel mapRow(ResultSet rs, int line) throws SQLException {
		FreindModel friendModel = new FreindModel();
		if (rs.getInt("id") != 0) {
			friendModel.setRowId(rs.getInt("id"));
		}

		if (rs.getInt("userId") != 0) {
			friendModel.setUserId(rs.getInt("userId"));
		}

		if (rs.getInt("friendId") != 0) {
			friendModel.setFriendId(rs.getInt("friendId"));
		}

		if (rs.getInt("isFollowing") != 0) {
			friendModel.setFollowingStatus(rs.getInt("isFollowing"));
		}

		if (rs.getInt("active") != 0) {
			friendModel.setActive(rs.getInt("active"));
		}

		if (!StringUtils.isEmpty(rs.getString("joinedDate"))) {

			friendModel.setJoinedDate(rs.getString("joinedDate"));
		}
		
		if(!StringUtils.isEmpty(rs.getString("shortHandName"))){
			friendModel.setUsername(rs.getString("shortHandName"));
		}

		return friendModel;
	}

}
