package com.storyshell.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.stereotype.Component;

import com.storyshell.contentdataservices.GenericExceptionHandler;
import com.storyshell.util.ResponseGenerator;

/**
 * 
 * @author Monarchpedo
 *
 */
@Component
@Path("/user/media")
public class MediaController {

	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFiles(final FormDataMultiPart multiPart) {
		List<FormDataBodyPart> bodyParts = multiPart.getFields("files");

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < bodyParts.size(); i++) {
			BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
			String fileName = bodyParts.get(i).getContentDisposition().getFileName();
			try {
				saveFile(bodyPartEntity.getInputStream(), fileName);
			} catch (IOException e) {
				e.printStackTrace();
				throw new GenericExceptionHandler(e.getMessage());
			}
		}
		return ResponseGenerator.generateResponse("files has been uploaded", Response.Status.CREATED);
	}

	@Path("/upload/audio")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadAudioFiles(final FormDataMultiPart multiPart) {
		return null;
	}

	@Path("/upload/video")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadVideoFiles(final FormDataMultiPart multiPart) {
		return null;
	}

	@Path("/download/images/{userId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response getImages(@PathParam("userId") int userId) {
		return null;
	}

	@Path("/download/image/{userId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response getProfileImage(@PathParam("userId") int userId) {
		return null;
	}

	@Path("/download/audio/{userId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response getAudio(@PathParam("userId") int userId) {
		return null;
	}

	@Path("/download/video/{userId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response getVideo(@PathParam("userId") int userId) {
		return null;
	}

	private void saveFile(InputStream file, String fileName) throws IOException {
		java.nio.file.Path path = FileSystems.getDefault().getPath("D:/log/images/" + fileName);
		java.nio.file.Files.copy(file, path);
	}
}
