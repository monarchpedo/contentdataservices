package com.storyshell.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.storyshell.contentdataservices.GenericExceptionHandler;
import com.storyshell.model.CommentDetail;
import com.storyshell.model.ContentHit;
import com.storyshell.model.FreindModel;
import com.storyshell.model.MediaList;
import com.storyshell.model.Message;
import com.storyshell.model.Post;
import com.storyshell.util.Constants;
import com.storyshell.util.GenericResultSetExtractor;
import com.storyshell.util.GenericRowMapper;
import com.storyshell.util.MediaMapper;
import com.storyshell.util.QueryMapper;

@Repository
public class ContentDataImpl implements ContentData {

	// private static final String serialId = "";

	@Inject
	private JdbcTemplate jdbcTemplate;

	private static final String AUTH_URL = "localhost:8000/oauth/v1/";

	@Override
	@Transactional
	public List<MediaList> getAllImages(int userId, int mediaType) {
		String query = com.storyshell.util.Constants.getMediaDetail;

		List<MediaList> mediaList = (List<MediaList>) jdbcTemplate.query(query, new Object[] {}, new MediaMapper());
		return mediaList;
	}

	@Override
	@Transactional
	public List<MediaList> getPostMedia(int postId) {
		List<MediaList> mediaList = null;
		String sql = "select * from mediastore where postId=?";
		try {
			mediaList = jdbcTemplate.query(sql, new Object[] { postId }, new GenericRowMapper(new MediaList()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return mediaList;
	}

	@Override
	public MediaList getProfileImage(int mediaId) {
		MediaList media = null;
		String sql = "select * from mediastore where id=?";
		try {
			media = (MediaList) jdbcTemplate.query(sql, new Object[] { mediaId },
					new GenericRowMapper(new MediaList()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return media;
	}

	@Override
	public Map<String, List<MediaList>> getAllOtherMedia(int userId) {
		Map<String, List<MediaList>> mediaList = null;
		String sql = "select * from mediastore where userid = ? order by createdDate DESC";
		try {
			mediaList = jdbcTemplate.query(sql, new Object[] { userId }, new GenericResultSetExtractor<MediaList>());
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return mediaList;
	}

	@Override
	@Transactional
	public int saveMedia(MediaList media) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("createdDate", dateTime);
		mapList.put("modifiedDate", dateTime);
		QueryMapper<MediaList> queryMapper = new QueryMapper<MediaList>();
		String insertQuery = queryMapper.getInsertQuery(media, "mediastore", mapList);
		Object[] values = queryMapper.getObjectValues();
		try {
			return jdbcTemplate.update(insertQuery, values);
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	/**
	 * start sectin of comments of post and other media
	 */

	@Override
	public int saveComment(CommentDetail comment) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("createdDate", dateTime);
		mapList.put("modifiedDate", dateTime);
		QueryMapper<CommentDetail> queryMapper = new QueryMapper<CommentDetail>();
		String insertQuery = queryMapper.getInsertQuery(comment, "commenttable", mapList);
		Object[] values = queryMapper.getObjectValues();
		try {
			return jdbcTemplate.update(insertQuery, values);
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public List<CommentDetail> getAllComments(int postId) {
		List<CommentDetail> commentList = null;
		String sql = "select * from commenttable where postId = ? order by createdDate DESC";
		try {
			commentList = jdbcTemplate.query(sql, new Object[] { postId }, new GenericRowMapper(new CommentDetail()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return commentList;
	}

	@Override
	public int updateComment(CommentDetail comment, int commentId) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("modifiedDate", dateTime);
		QueryMapper<CommentDetail> queryMapper = new QueryMapper<CommentDetail>();
		String updateQuery = queryMapper.getUpdateQuery(comment, "commenttable", mapList);
		Object[] values = queryMapper.getObjectValues();
		String sql = updateQuery + "where id=" + commentId;
		try {
			return jdbcTemplate.update(sql, values);
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public int deleteComment(int commentId, int userId) {
		String sql = "delete from commenttable where id = ? and userId = ?";
		try {
			return jdbcTemplate.update(sql, new Object[] { commentId, userId });
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public int deleteCommentByOwner(int commentId, int postId) {
		String sql = "delete from commenttable where id = ? and postId = ?";
		try {
			return jdbcTemplate.update(sql, new Object[] { commentId, postId });
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public List<CommentDetail> getComments(int parentId) {
		List<CommentDetail> commentList = null;
		String sql = "select * from commenttable where parentId = ? order by createdDate DESC";
		try {
			commentList = jdbcTemplate.query(sql, new Object[] { parentId }, new GenericRowMapper(new CommentDetail()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return commentList;
	}

	/**
	 * end of commentdetail section of any post
	 */

	/**
	 * start of userpost content of contentdataservice
	 */

	@Override
	@Transactional
	public int savePost(Post post) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("createdDate", dateTime);
		mapList.put("modifiedDate", dateTime);
		QueryMapper<Post> queryMapper = new QueryMapper<Post>();
		String insertQuery = queryMapper.getInsertQuery(post, "userpost", mapList);
		Object[] values = queryMapper.getObjectValues();
		try {
			return jdbcTemplate.update(insertQuery, values);
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public int updatePost(int postId, Post post) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("modifiedDate", dateTime);
		QueryMapper<Post> queryMapper = new QueryMapper<Post>();
		String updateQuery = queryMapper.getUpdateQuery(post, "userpost", mapList);
		Object[] values = queryMapper.getObjectValues();
		String sql = updateQuery + "where id=" + postId;
		try {
			return jdbcTemplate.update(sql, values);
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public List<Post> getAllPost(int userId) {
		List<Post> postList = null;
		String sql = "select * from `userpost` where userId = " + userId;
		try {
			postList = jdbcTemplate.query(sql, new GenericRowMapper(new Post()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return postList;
	}

	@Override
	public List<Post> getPostByInterest(String interestValue, int offset) {
		String sql = "Select * from `userpost` where postSectionId like ? order by createdDate DESC limit ?,10";
		String interest = interestValue + "%";
		List<Post> postList = null;
		try {
			postList = jdbcTemplate.query(sql, new Object[] { interest, offset - 1 }, new GenericRowMapper(new Post()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Post> getAllChannelPost(String pageId) {
		List<Post> postList = null;
		String sql = "select * from `userpost` where pageId= ?";
		try {
			postList = jdbcTemplate.query(sql, new Object[] { pageId }, new GenericRowMapper(new Post()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return postList;
	}

	@Override
	public List<Post> getPostList(List<String> interest, int offset) {
		List<Post> postList = null;
		StringBuilder selectQuery = new StringBuilder();
		Object[] object = (Object[]) new Object();
		int i = 0;
		selectQuery.append("Select * from `userpost` where ");
		for (String interestValue : interest) {
			selectQuery.append("postSectionId like ? or ");
			object[i] = interestValue + "%";
			i++;
		}
		String subQuery = selectQuery.toString();
		int lastIndex = selectQuery.lastIndexOf("or");
		String subString = selectQuery.substring(0, lastIndex);
		String sql = subString + "order by createdDate DESC limit ?,10";
		try {
			postList = jdbcTemplate.query(sql, object, new GenericRowMapper(new Post()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return postList;
	}

	/**
	 * end of user post section list of contentdataservice
	 */

	/**
	 * start of hit section
	 */
	@Override
	public int saveLikesHit(ContentHit hit) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		QueryMapper<ContentHit> queryMapper = new QueryMapper<ContentHit>();
		String insertQuery = queryMapper.getInsertQuery(hit, "favlikestable", mapList);
		Object[] values = queryMapper.getObjectValues();
		try {
			return jdbcTemplate.update(insertQuery, values);
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public int saveUnLikesHit(ContentHit hit) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		QueryMapper<ContentHit> queryMapper = new QueryMapper<ContentHit>();
		String insertQuery = queryMapper.getInsertQuery(hit, "unfavlikestable", mapList);
		Object[] values = queryMapper.getObjectValues();
		try {
			return jdbcTemplate.update(insertQuery, values);
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public List<ContentHit> getLikesHit(int postId, int type) {
		List<ContentHit> hitList = null;
		String sql = "select * from favlikestable where postId = ? and type = ?";
		try {
			hitList = jdbcTemplate.query(sql, new Object[] { postId, type },
					new GenericRowMapper<ContentHit>(new ContentHit()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return hitList;
	}

	@Override
	public List<ContentHit> getUnLikesHit(int postId, int type) {
		List<ContentHit> hitList = null;
		String sql = "select * from unfavlikestable where postId = ? and type = ?";
		try {
			hitList = jdbcTemplate.query(sql, new Object[] { postId, type },
					new GenericRowMapper<ContentHit>(new ContentHit()));
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return hitList;
	}

	@Override
	public int deleteLikesHit(int postId, int userId, int type) {
		String sql = "delete from favlikestable where postId = ? and userId = ? and type = ?";
		try {
			return jdbcTemplate.update(sql, new Object[] { postId, userId, type });
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public int deleteUnLikesHit(int postId, int userId, int type) {
		String sql = "delete from unfavlikestable where postId = ? and userId = ? and type = ?";
		try {
			return jdbcTemplate.update(sql, new Object[] { postId, userId, type });
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	/**
	 * end of hit section
	 */

	/**
	 * start section of friend, group, chat message, group chat
	 */

	@Override
	public int saveNetwork(FreindModel friendModel) {		
		return 0;
	}

	@Override
	public int saveMessage(Message message) {
		return 0;
	}

	@Override
	public List<Message> getAllMessage(int userId) {
		return null;
	}

	@Override
	public List<Message> getPToPMessage(int userId, int frinedId) {
		return null;
	}

	@Override
	public List<FreindModel> getAllFriendList(int userId) {
		return null;
	}

	@Override
	public List<FreindModel> getAllFollowers(int userId) {
		return null;
	}

	/**
	 * end of friend,group,chat message, group chat section
	 */
}
