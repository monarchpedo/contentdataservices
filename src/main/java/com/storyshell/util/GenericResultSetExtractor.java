package com.storyshell.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.storyshell.model.MediaList;

public class GenericResultSetExtractor<T> implements ResultSetExtractor<Map<String, List<T>>> {

	private Map<String, List<T>> mapList;

	public GenericResultSetExtractor() {
		mapList = new HashMap<String, List<T>>();
	}

	@Override
	public Map<String, List<T>> extractData(ResultSet rs) throws SQLException, DataAccessException {

		while (rs.next()) {
			MediaList media = new MediaList();
			String mediaType = rs.getString("mediaType");
			List<T> list = mapList.get(mediaType);
			if (rs.getInt("Id") != 0) {
				media.setId(rs.getInt("id"));
			}

			if (rs.getInt("userId") != 0) {
				media.setUserId(rs.getInt("userId"));
			}

			if (rs.getInt("postId") != 0) {
				media.setPostId(rs.getInt("postId"));
			}

			if (!StringUtils.isEmpty(rs.getString("mediaLink"))) {
				media.setMediaLink(rs.getString("mediaLink"));
			}

			if (!StringUtils.isEmpty(rs.getString("location"))) {
				media.setLocation(rs.getString("location"));
			}

			if (!StringUtils.isEmpty(rs.getString("mediaType"))) {
				media.setMediaType(rs.getString("mediaType"));
			}

			if (!StringUtils.isEmpty(rs.getDate("modifiedDate"))) {
				media.setModifiedDate(rs.getDate("modifiedDate"));
			}

			if (!StringUtils.isEmpty(rs.getDate("createdDate"))) {
				media.setCreatedDate(rs.getDate("createdDate"));
			}

			media.setIsAvailabel(rs.getInt("isAvailabel"));

			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList<T>();
				list.add((T) media);
				mapList.put(mediaType, list);
			} else {
				list.add((T) media);
				mapList.put(mediaType, list);
			}
		}
		return mapList;
	}

}
