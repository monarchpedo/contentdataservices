package com.storyshell.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.storyshell.model.CommentDetail;
import com.storyshell.model.ContentHit;
import com.storyshell.model.FreindModel;
import com.storyshell.model.GroupNetwork;
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
		mediaList = jdbcTemplate.query(sql, new Object[] { postId }, new GenericRowMapper(new MediaList()));
		return mediaList;
	}

	@Override
	@Transactional
	public MediaList getProfileImage(int mediaId) {
		MediaList media = null;
		String sql = "select * from mediastore where id=?";
		media = (MediaList) jdbcTemplate.query(sql, new Object[] { mediaId }, new GenericRowMapper(new MediaList()));
		return media;
	}

	@Override
	@Transactional
	public Map<String, List<MediaList>> getAllOtherMedia(int userId) {
		Map<String, List<MediaList>> mediaList = null;
		String sql = "select * from mediastore where userid = ? order by createdDate DESC";
		mediaList = jdbcTemplate.query(sql, new Object[] { userId }, new GenericResultSetExtractor<MediaList>());
		return mediaList;
	}

	@Override
	@Transactional
	public int saveMedia(MediaList media) {
		KeyHolder holder = new GeneratedKeyHolder();
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("createdDate", dateTime);
		mapList.put("modifiedDate", dateTime);
		QueryMapper<MediaList> queryMapper = new QueryMapper<MediaList>();
		String insertQuery = queryMapper.getInsertQuery(media, "mediastore", mapList);
		Object[] values = queryMapper.getObjectValues();
		int i = jdbcTemplate.update(insertQuery, values, holder);
		if (i == 0) {
			return -1;
		}
		return holder.getKey().intValue();
	}

	/**
	 * start sectin of comments of post and other media
	 */

	@Override
	@Transactional
	public long saveComment(CommentDetail comment) {
		KeyHolder holder = new GeneratedKeyHolder();
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("createdDate", dateTime);
		mapList.put("modifiedDate", dateTime);
		QueryMapper<CommentDetail> queryMapper = new QueryMapper<CommentDetail>();
		String insertQuery = queryMapper.getInsertQuery(comment, "commenttable", mapList);
		Object[] values = queryMapper.getObjectValues();
		int i = jdbcTemplate.update(insertQuery, values, holder);
		if (i == 0) {
			return -1;
		}
		return holder.getKey().longValue();
	}

	@Override
	@Transactional
	public List<CommentDetail> getAllComments(int postId) {
		List<CommentDetail> commentList = null;
		String sql = "select * from commenttable where postId = ? order by createdDate DESC";
		commentList = jdbcTemplate.query(sql, new Object[] { postId }, new GenericRowMapper(new CommentDetail()));
		return commentList;
	}

	@Override
	@Transactional
	public int updateComment(CommentDetail comment, int commentId) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("modifiedDate", dateTime);
		QueryMapper<CommentDetail> queryMapper = new QueryMapper<CommentDetail>();
		String updateQuery = queryMapper.getUpdateQuery(comment, "commenttable", mapList);
		Object[] values = queryMapper.getObjectValues();
		String sql = updateQuery + "where id=" + commentId;
		return jdbcTemplate.update(sql, values);
	}

	@Override
	@Transactional
	public int deleteComment(int commentId, int userId) {
		String sql = "delete from commenttable where id = ? and userId = ?";
		return jdbcTemplate.update(sql, new Object[] { commentId, userId });
	}

	@Override
	@Transactional
	public int deleteCommentByOwner(int commentId, int postId) {
		String sql = "delete from commenttable where id = ? and postId = ?";
		return jdbcTemplate.update(sql, new Object[] { commentId, postId });
	}

	@Override
	@Transactional
	public List<CommentDetail> getComments(int parentId) {
		List<CommentDetail> commentList = null;
		String sql = "select * from commenttable where parentId = ? order by createdDate DESC";
		commentList = jdbcTemplate.query(sql, new Object[] { parentId }, new GenericRowMapper(new CommentDetail()));
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
	public long savePost(Post post) {
		KeyHolder holder = new GeneratedKeyHolder();
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("createdDate", dateTime);
		mapList.put("modifiedDate", dateTime);
		QueryMapper<Post> queryMapper = new QueryMapper<Post>();
		String insertQuery = queryMapper.getInsertQuery(post, "userpost", mapList);
		Object[] values = queryMapper.getObjectValues();
		int i = jdbcTemplate.update(insertQuery, values, holder);
		if (i == 0) {
			return -1;
		}
		return holder.getKey().longValue();
	}

	@Override
	@Transactional
	public int updatePost(int postId, Post post) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("modifiedDate", dateTime);
		QueryMapper<Post> queryMapper = new QueryMapper<Post>();
		String updateQuery = queryMapper.getUpdateQuery(post, "userpost", mapList);
		Object[] values = queryMapper.getObjectValues();
		String sql = updateQuery + "where id=" + postId;
		return jdbcTemplate.update(sql, values);
	}

	@Override
	@Transactional
	public int deletePost(int postId) {
		String sql = "delete from `userpost` where postId = " + postId;
		return jdbcTemplate.update(sql);
	}

	@Override
	@Transactional
	public List<Post> getAllPost(int userId, int offset) {
		List<Post> postList = null;
		String sql = "select * from `userpost` where userId = ? order by createdDate DESC limit ?,10";
		postList = jdbcTemplate.query(sql, new Object[] { userId, offset }, new GenericRowMapper(new Post()));
		return postList;
	}

	@Override
	@Transactional
	public List<Post> getPostByInterest(String interestValue, int offset) {
		String sql = "Select * from `userpost` where postSectionId like ? order by createdDate DESC limit ?,10";
		String interest = interestValue + "%";
		List<Post> postList = null;
		postList = jdbcTemplate.query(sql, new Object[] { interest, offset - 1 }, new GenericRowMapper(new Post()));
		return postList;
	}

	@Override
	@Transactional
	public List<Post> getPostByUserInterest(int userId, String interestValue, int offset) {
		String sql = "Select * from `userpost` where userId = ? and postSectionId like ? order by createdDate DESC limit ?,10";
		String interest = interestValue + "%";
		List<Post> postList = null;
		postList = jdbcTemplate.query(sql, new Object[] { userId, interest, offset - 1 },
				new GenericRowMapper(new Post()));
		return postList;
	}

	@Override
	@Transactional
	public List<Post> getAllChannelPost(String pageId, int offset) {
		List<Post> postList = null;
		String sql = "select * from `userpost` where pageId= ? order by createdDate DESC limit ?,10";
		postList = jdbcTemplate.query(sql, new Object[] { pageId, offset }, new GenericRowMapper(new Post()));
		return postList;
	}

	@Override
	@Transactional
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
		postList = jdbcTemplate.query(sql, object, new GenericRowMapper(new Post()));
		return postList;
	}

	/**
	 * end of user post section list of contentdataservice
	 */

	/**
	 * start of hit section
	 */
	@Override
	@Transactional
	public int saveLikesHit(ContentHit hit) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		QueryMapper<ContentHit> queryMapper = new QueryMapper<ContentHit>();
		String insertQuery = queryMapper.getInsertQuery(hit, "favlikestable", mapList);
		Object[] values = queryMapper.getObjectValues();
		return jdbcTemplate.update(insertQuery, values);
	}

	@Override
	@Transactional
	public int saveUnLikesHit(ContentHit hit) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		QueryMapper<ContentHit> queryMapper = new QueryMapper<ContentHit>();
		String insertQuery = queryMapper.getInsertQuery(hit, "unfavlikestable", mapList);
		Object[] values = queryMapper.getObjectValues();
		return jdbcTemplate.update(insertQuery, values);
	}

	@Override
	@Transactional
	public List<ContentHit> getLikesHit(int postId, int type) {
		List<ContentHit> hitList = null;
		String sql = "select * from `favlikestable` where postId = ? and type = ?";

		hitList = jdbcTemplate.query(sql, new Object[] { postId, type },
				new GenericRowMapper<ContentHit>(new ContentHit()));

		return hitList;
	}

	@Override
	@Transactional
	public List<ContentHit> getUnLikesHit(int postId, int type) {
		List<ContentHit> hitList = null;
		String sql = "select * from `unfavlikestable` where postId = ? and type = ?";
		hitList = jdbcTemplate.query(sql, new Object[] { postId, type },
				new GenericRowMapper<ContentHit>(new ContentHit()));
		return hitList;
	}

	@Override
	@Transactional
	public int deleteLikesHit(int postId, int userId, int type) {
		String sql = "delete from `favlikestable` where postId = ? and userId = ? and type = ?";
		return jdbcTemplate.update(sql, new Object[] { postId, userId, type });
	}

	@Override
	@Transactional
	public int deleteUnLikesHit(int postId, int userId, int type) {
		String sql = "delete from `unfavlikestable` where postId = ? and userId = ? and type = ?";
		return jdbcTemplate.update(sql, new Object[] { postId, userId, type });
	}

	/**
	 * end of hit section
	 */

	/**
	 * start section of friend, group, chat message, group chat
	 */

	@Override
	@Transactional
	public int saveNetwork(FreindModel friendModel) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("createdDate", dateTime);
		QueryMapper<FreindModel> queryMapper = new QueryMapper<FreindModel>();
		String insertQuery = queryMapper.getInsertQuery(friendModel, "friendlist", mapList);
		Object[] values = queryMapper.getObjectValues();
		return jdbcTemplate.update(insertQuery, values);
	}

	@Override
	@Transactional
	public int saveMessage(Message message) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("createdDate", dateTime);
		mapList.put("modifiedDate", dateTime);
		QueryMapper<Message> queryMapper = new QueryMapper<Message>();
		String insertQuery = queryMapper.getInsertQuery(message, "message", mapList);
		Object[] values = queryMapper.getObjectValues();
		return jdbcTemplate.update(insertQuery, values);
	}

	@Override
	@Transactional
	public int createGroup(GroupNetwork grpNetwork) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		mapList.put("createdDate", dateTime);
		QueryMapper<GroupNetwork> queryMapper = new QueryMapper<GroupNetwork>();
		String insertQuery = queryMapper.getInsertQuery(grpNetwork, "grouplist", mapList);
		Object[] values = queryMapper.getObjectValues();
		return jdbcTemplate.update(insertQuery, values);
	}

	@Override
	@Transactional
	public List<Message> getMessage(int userId, int offset) {
		return null;
	}

	@Override
	@Transactional
	public List<Message> getGroupMessage(int groupId, int offset) {
		List<Message> messageList = null;
		String sql = "select * from `message` where groupId = ? order by createdDate desc limit ?,10";
		messageList = jdbcTemplate.query(sql, new Object[] { groupId, offset },
				new GenericRowMapper<Message>(new Message()));
		return messageList;
	}

	@Override
	@Transactional
	public List<Message> getPToPMessage(int userId, int frinedId, int offset) {
		List<Message> messageList = null;
		String sql = "select * from `message` where userId = ? and receiptId = ? order by createdDate desc limit ?,10";
		messageList = jdbcTemplate.query(sql, new Object[] { userId, frinedId, offset },
				new GenericRowMapper<Message>(new Message()));
		return messageList;
	}

	@Override
	@Transactional
	public List<FreindModel> getAllFriendList(int userId) {
		List<FreindModel> friendList = null;
		String sql = "select * from `friendlist` where userId = ? and active = 1";
		friendList = jdbcTemplate.query(sql, new Object[] { userId },
				new GenericRowMapper<FreindModel>(new FreindModel()));
		return friendList;
	}

	@Override
	@Transactional
	public List<FreindModel> getAllFollowers(int userId) {
		List<FreindModel> friendList = null;
		String sql = "select * from `friendlist` where userId = ? and isFollowing = 1";

		friendList = jdbcTemplate.query(sql, new Object[] { userId },
				new GenericRowMapper<FreindModel>(new FreindModel()));

		return friendList;
	}

	@Override
	@Transactional
	public List<GroupNetwork> getAllGroup(int userId) {
		List<GroupNetwork> groupList = null;
		String sql = "select * from `grouplist` where userId = ? and active = 1";

		groupList = jdbcTemplate.query(sql, new Object[] { userId },
				new GenericRowMapper<GroupNetwork>(new GroupNetwork()));

		return groupList;
	}

	@Override
	@Transactional
	public List<Integer> getFriendChatId(int userId) {
		String sql = "select unique `receiptId` from message where userId = ? order by createdDate desc";
		return jdbcTemplate.queryForList(sql, new Object[] { userId }, Integer.class);
	}

	/**
	 * end of friend,group,chat message, group chat section
	 */
}
