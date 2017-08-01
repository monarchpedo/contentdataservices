package com.storyshell.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import com.storyshell.model.Message;

public class MessageMapper implements RowMapper<Message> {

	@Override
	public Message mapRow(ResultSet rs, int line) throws SQLException {
		// TODO Auto-generated method stub
		Message message = new Message();
		if (rs.getInt("id") != 0) {
			message.setMsgId(rs.getInt("id"));
		}

		if (rs.getInt("userId") != 0) {
			message.setUserId(rs.getInt("userId"));
		}

		if (rs.getInt("mediaId") != 0) {
			message.setMediaId(rs.getInt("mediaId"));
		}

		if (rs.getInt("receiptId") != 0) {
			message.setReceiptId(rs.getInt("receiptId"));
		}

		if (rs.getInt("groupId") != 0) {
			message.setGroupId(rs.getInt("groupId"));
		}

		if (rs.getInt("isActive") != 0) {
			message.setIsActive(rs.getInt("isActive"));
		}

		if (!StringUtils.isEmpty(rs.getString("messageContent"))) {
			message.setMessage(rs.getString("messageContent"));
		}

		if (!StringUtils.isEmpty(rs.getString("createdDate"))) {
			message.setCreatedDate(rs.getString("createdDate"));
		}

		return message;
	}

}
