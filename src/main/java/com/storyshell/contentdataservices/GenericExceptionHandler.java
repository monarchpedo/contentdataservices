package com.storyshell.contentdataservices;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class GenericExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private static Logger LOG = LoggerFactory.getLogger(GenericExceptionHandler.class); 

	public GenericExceptionHandler(String message) {
		this.message = message;
	}

	public Response toResponse(RuntimeException exception) {
		LOG.error(this.getMessage());
		return Response.serverError().entity(this.getMessage()).build();
	}

	public String getMessage() {
		return this.message;
	}

}
