package com.storyshell.contentdataservices;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericExceptionMapper<T> implements ExceptionMapper<Throwable> {
	
	private static Logger LOG = LoggerFactory.getLogger(GenericExceptionMapper.class);

	public Response toResponse(Throwable exception) {
		// TODO Auto-generated method stub
		LOG.error(exception.getMessage());
		return Response.serverError().entity(exception.getMessage()).build();
	}

}