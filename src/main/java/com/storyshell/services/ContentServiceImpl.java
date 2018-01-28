package com.storyshell.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.storyshell.contentdataservices.GenericExceptionHandler;
import com.storyshell.dao.ContentData;
import com.storyshell.dao.ElasticData;
import com.storyshell.model.MediaList;
import com.storyshell.model.Post;
import com.storyshell.util.FileValidation;
import com.storyshell.util.GenericRestTemplateCall;
import com.storyshell.util.ResponseGenerator;

@Service
public class ContentServiceImpl implements ContentService {

	@Inject
	private ContentData contentData;
	private static final String AUTH_URL = "localhost:8000/oauth/v1/";

	@Value("${upload.location}")
	private String dirName;

	@Inject
	private ElasticData elasticData;

	/**
	 * save post of user of any type either normal post, channel post and
	 * storyshell post
	 */
	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response savePost(Post post, List<FormDataBodyPart> bodyParts) throws GenericExceptionHandler {
		try {
			GenericRestTemplateCall<String, Boolean> restTemplate = new GenericRestTemplateCall<String, Boolean>();
			boolean isExists = (boolean) restTemplate.doGetExecute("/user/" + post.getUserId() + "/check", null,
					Boolean.class);
			if (isExists == false) {
				throw new GenericExceptionHandler("userId does not exists");
			}
			long saveResultId = contentData.savePost(post);
			if (saveResultId == -1) {
				return ResponseGenerator.generateResponse("Error in saving post,please try again...",
						Response.Status.CONFLICT);
			}

			saveMedia(bodyParts, saveResultId, post.getUserId());
			
			//after susccesfull saving we are saving data to elasticsearch repository
			post.setId((int) saveResultId);
			elasticData.save(post);
			
			return ResponseGenerator.generateResponse("post has been saved", Response.Status.CREATED);
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response deletePost(int postId) {
		int result = contentData.deletePost(postId);
		return ResponseGenerator.generateResponse(result, Response.Status.MOVED_PERMANENTLY);
	}

	/**
	 * 
	 */
	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response getPostByUserId(int userId, int offset) {
		List<Post> userPost = contentData.getAllPost(userId, offset);
		userPost.forEach(postRow -> {
			if (postRow.getIsMediaContain() == 1) {
				List<MediaList> mediaLists = contentData.getPostMedia(postRow.getId());
				postRow.setMediaList(mediaLists);
			}
		});
		return ResponseGenerator.generateResponse(userPost, Response.Status.ACCEPTED);
	}

	/**
	 * 
	 */
	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response getPostByPageId(int pageId, int offset) {
		List<Post> channelPost = contentData.getAllChannelPost(String.valueOf(pageId), offset);
		channelPost.forEach(postRow -> {
			if (postRow.getIsMediaContain() == 1) {
				List<MediaList> mediaLists = contentData.getPostMedia(postRow.getId());
				postRow.setMediaList(mediaLists);
			}
		});
		return ResponseGenerator.generateResponse(channelPost, Response.Status.ACCEPTED);
	}

	/**
	 * 
	 */
	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response getPostByUserSection(String sectionId, int userId, int offset) {
		List<Post> postList = null;
		postList = contentData.getPostByUserInterest(userId, sectionId, offset);
		postList.forEach(postRow -> {
			if (postRow.getIsMediaContain() == 1) {
				List<MediaList> mediaLists = contentData.getPostMedia(postRow.getId());
				postRow.setMediaList(mediaLists);
			}
		});
		return ResponseGenerator.generateResponse(postList, Response.Status.ACCEPTED);
	}

	/**
	 * 
	 */
	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response getPostByChannelSection(String sectionId, int pageId, String channelName, int offset) {
		List<Post> postList = null;
		return null;
	}

	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response getPostBySection(String sectionId, int offset) {
		List<Post> postList = null;
		postList = contentData.getPostByInterest(sectionId, offset);
		postList.forEach(postRow -> {
			if (postRow.getIsMediaContain() == 1) {
				List<MediaList> mediaLists = contentData.getPostMedia(postRow.getId());
				postRow.setMediaList(mediaLists);
			}
		});
		return ResponseGenerator.generateResponse(postList, Response.Status.ACCEPTED);
	}

	/**
	 * 
	 * @param bodyParts
	 * @param postId
	 * @param userId
	 * @throws IOException
	 */
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	private void saveMedia(List<FormDataBodyPart> bodyParts, long postId, int userId) throws IOException {
		// Path dir = Paths.get(dirName);
		String locationDir = dirName;
		MediaList media = new MediaList();
		for (int i = 0; i < bodyParts.size(); i++) {
			String fileName = bodyParts.get(i).getContentDisposition().getFileName();

			if (!FileValidation.validFileNameFormat(fileName)) {
				throw new GenericExceptionHandler(
						"not valid fileName, please send correct fileName to save post of particular interest");
			}
			int mediaType = FileValidation.checkMediaType(fileName);
			String location = locationDir + userId + "/media/" + mediaType + "/";
			createDirectory(locationDir, userId, mediaType);
			setMedia(media, userId, postId, location, mediaType, fileName);
			int mediaResultId = contentData.saveMedia(media);
			if (mediaResultId == -1) {
				throw new GenericExceptionHandler("error in saving post, please try again");
			}
			BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
			String file = location + fileName;
			saveFile(bodyPartEntity.getInputStream(), file);
		}
	}

	/**
	 * 
	 * @param media
	 * @param userId
	 * @param postId
	 * @param location
	 *            check the int value of postId, parsed, so make change in
	 *            future
	 */
	private void setMedia(MediaList media, int userId, long postId, String location, int mediaType, String fileName) {
		media.setPostId((int) postId);
		media.setUserId(userId);
		media.setLocation(location);
		media.setIsAvailabel(1);
		media.setMediaType(mediaType);
		media.setFileName(fileName);
	}

	/**
	 * 
	 * @param file
	 * @param fileName
	 * @throws IOException
	 */
	private void saveFile(InputStream file, String dirName) throws IOException {
		java.nio.file.Path path = FileSystems.getDefault().getPath(dirName);
		java.nio.file.Files.copy(file, path);
	}

	/**
	 * 
	 * @param userPost
	 * @return
	 */
	private Response setMediaPost(List<Post> userPost) {
		MultiPart multiPart = new MultiPart();
		Map<Integer, FormDataBodyPart> formDataBodyPartMap = new HashMap<Integer, FormDataBodyPart>();
		List<FormDataBodyPart> formDataBodyParts = new ArrayList<FormDataBodyPart>();
		int count = 0;
		for (Post post : userPost) {
			if (post.getIsMediaContain() == 1) {
				List<MediaList> mediaLists = contentData.getPostMedia(post.getId());
				for (MediaList media : mediaLists) {
					FormDataBodyPart dataPart = new FormDataBodyPart();
					String fileName = media.getLocation() + media.getFileName() + "." + media.getExtension();
					File file = new File(fileName);
					dataPart.setEntity(file);
					ContentDisposition documentPart = ContentDisposition.type("attachment").fileName(fileName).build();
					dataPart.setContentDisposition(documentPart);
					formDataBodyParts.add(dataPart);
				}
			}
			multiPart.setEntity(formDataBodyParts);
		}
		return Response.status(Response.Status.ACCEPTED).entity(multiPart).type(MediaType.MULTIPART_FORM_DATA).build();
	}

	/**
	 * 
	 */
	private boolean createDirectory(String locationDir, int userId, int mediaType) {

		String userIdDir = locationDir + String.valueOf(userId);
		File userIdDirCheck = new File(userIdDir);
		if (!userIdDirCheck.exists()) {
			userIdDirCheck.mkdir();
		}

		String mediaDir = userIdDir + "/media";
		File mediaDirCheck = new File(mediaDir);
		if (!mediaDirCheck.exists()) {
			mediaDirCheck.mkdir();
		}

		String mediaTypeDir = mediaDir + "/" + String.valueOf(mediaType);
		File mediaTypeDirCheck = new File(mediaTypeDir);
		if (!mediaTypeDirCheck.exists()) {
			mediaTypeDirCheck.mkdir();
		}
		return true;

	}
}
