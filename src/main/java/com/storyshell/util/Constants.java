package com.storyshell.util;

public class Constants {

	public static String columnName = "";
	public static String columnValues = "";
	public static String friendIdList = "";

	public static final String getMediaDetail = "select * from mediastore where userId = ? and mediaType = ?";
	
	public static final String getMediaDetailById = "select * from mediastore where id = ?";

	public static final String getMediaDetailByPost = "select * from mediastore where userId = ? and postId = ? and mediaType = ?";

	public static final String getMediaDetailByMessage = "select * from mediastore where userId = ? and  messageId = ? and mediaType = ?";

	public static final String getCommentDetail = "select * from commenttable where userId = ? and parentLevel = ?";

	public static final String getCommentDetailByParent = "select * from commenttable where userId = ? and parentId = ? and parentLevel =?";

	public static final String getFriendList = "select * from friendlist where userId  = ? and active = 1";

	public static final String getFollowerList = "select * from friendlist where userId = ? and isFollowing = 1 and active = 1";

	public static final String getMessageList = "select * from message from where userId = ? ";

	public static final String getMessageListByFriend = "select * from message where userId = ? and friendId = ?";

	public static final String getPostList = "select * from userpost where postsection = 1 ";

	public static final String getPostByPostId = "select * from userpost where postId = ? ";
	/**
	 * it may give result on the basis of confession,storyshell,public post
	 */
	public static final String getPostByInterest = "select * from userpost where postsection = ? ";
	/**
	 * it may give result on the basis of confession,storyshell,public post for
	 * a specific user
	 */
	public static final String getPostByInterestByUserId = "select * from userpost where postsection = ? and userId = ?";
	/**
	 * it may give result on the basis of interest section from public post
	 */
	public static final String getPostByPostType = "select * from userpost  where posttype =? and postsection = 1";

	public static final String getPostByPostTypeAndUserId = "select * from userpost where userId = ? and posttype = ? and postsection = 1";

	public static final String saveMedia = "insert into mediastore (" + columnName + ") values (" + columnValues
			+ ") on duplicate update mediastore set ";

	public static final String saveMessage = "insert into message (" + columnName + ") values (" + columnValues
			+ ") on duplicate update message set";

	public static final String saveFriendList = "insert into friendlist (" + columnName + ") values (" + columnValues
			+ ") on duplicate update friendlist set";

	public static final String savePost = "insert into userpost (" + columnName + ") values (" + columnValues
			+ ") on duplicate update userpost set";

	public static final String saveComment = "inset into commenttable (" + columnName + ") values (" + columnValues
			+ ") on duplicate update commenttable set";

	public static final String deleteComment = "delete  from  commenttable where commentId = ? or parentId = ?";

	public static final String deletePost = "delete from userpost where postId = ? ";

	public static final String deleteFriendList = "delete from friendlist where userId = ? and friendId in ("
			+ friendIdList + " )";

	public static final String deleteCommentByPost = "delete from commenttable where postId = ?";

	public static final String unFriend = "update friendlist set active = 0 where userId = ? and friendId = ?";

	public static final String unFollow = "update friendlist set isFollowing = 0 where userId = ? and friendId = ?";

	public static final String block = "insert into blockhistory (" + columnName + ") values (" + columnValues + ")";

	public static final String unblock = "delete  from blockhistory where userId = ? and friendId = ?";

	public static final String geBlockHistory = "select isBlock where userId = ? and friendId = ?";

	public static final String deleteMessage = "delete from message where messageId = ?";

	public static final String deleteMedia = "delete from mediastore where mediaId = ?";

}
