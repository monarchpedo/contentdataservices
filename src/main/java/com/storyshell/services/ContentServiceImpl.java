package com.storyshell.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.storyshell.contentdataservices.GenericExceptionHandler;
import com.storyshell.dao.ContentData;
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
			return ResponseGenerator.generateResponse("post has been saved", Response.Status.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
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
		return setMediaPost(userPost);
	}

	/**
	 * 
	 */
	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public Response getPostByPageId(int pageId, int offset) {
		List<Post> channelPost = contentData.getAllChannelPost(String.valueOf(pageId), offset);
		return setMediaPost(channelPost);
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
		return setMediaPost(postList);
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
		Path dir = Paths.get(dirName);
		String locationDir = dir.toString();
		MediaList media = new MediaList();
		for (int i = 0; i < bodyParts.size(); i++) {
			String fileName = bodyParts.get(i).getContentDisposition().getFileName();
			if (!FileValidation.validFileNameFormat(fileName)) {
				throw new GenericExceptionHandler(
						"not valid fileName, please send correct fileName to save post of particular interest");
			}
			int mediaType = FileValidation.checkMediaType(fileName);
			String location = locationDir + userId + "/media/" + mediaType + "/";
			setMedia(media, userId, postId, location, mediaType);
			int mediaResultId = contentData.saveMedia(media);
			if (mediaResultId == -1) {
				throw new GenericExceptionHandler("error in saving post, please try again");
			}
			BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
			String file = mediaResultId + fileName;
			saveFile(bodyPartEntity.getInputStream(), location, file);
		}
	}

	/**
	 * 
	 * @param media
	 * @param userId
	 * @param postId
	 * @param location
	 *            check the int value of postId, prased, so make change in
	 *            future
	 */
	private void setMedia(MediaList media, int userId, long postId, String location, int mediaType) {
		media.setPostId((int) postId);
		media.setUserId(userId);
		media.setLocation(location);
		media.setIsAvailabel(1);
		media.setMediaType(mediaType);
	}

	/**
	 * 
	 * @param file
	 * @param fileName
	 * @throws IOException
	 */
	private void saveFile(InputStream file, String dirName, String fileName) throws IOException {
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
		List<FormDataMultiPart> formDataMultiParts = new ArrayList<FormDataMultiPart>();
		int count = 0;
		for (Post post : userPost) {
			if (post.getIsMediaContain() == 1) {
				List<FormDataBodyPart> formDataBodyParts = new ArrayList<FormDataBodyPart>();
				FormDataBodyPart dataPart = new FormDataBodyPart();
				List<MediaList> mediaLists = contentData.getPostMedia(post.getId());
				for (MediaList media : mediaLists) {
					String fileName = media.getLocation() + media.getId() + media.getExtension();
					File file = new File(fileName);
					dataPart.setEntity(file);
					ContentDisposition documentPart = ContentDisposition.type("attachment").fileName(fileName).build();
					dataPart.setContentDisposition(documentPart);
					formDataBodyParts.add(dataPart);
				}
				FormDataMultiPart formMultiPart = new FormDataMultiPart();
				formMultiPart.setEntity(post);
				formDataBodyParts.set(count++, dataPart);
				formDataMultiParts.add(formMultiPart);
			}
			multiPart.setEntity(formDataMultiParts);
		}
		return Response.status(Response.Status.ACCEPTED).entity(multiPart).type(MediaType.MULTIPART_FORM_DATA).build();
	}
}
