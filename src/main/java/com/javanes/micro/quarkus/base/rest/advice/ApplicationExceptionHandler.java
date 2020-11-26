package com.javanes.micro.quarkus.base.rest.advice;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javanes.micro.quarkus.base.exception.ApplicationException;
import com.javanes.micro.quarkus.base.rest.pojo.ExceptionResponse;

public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {

    final static Logger LOG = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @Override
    public Response toResponse(ApplicationException exception) {
        LOG.warn("UNHANDLED_CASE", exception);
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(exception.getCode());
        response.setMessage(exception.getMessage());
        return Response.serverError().entity(response).build();
    }
    
}
