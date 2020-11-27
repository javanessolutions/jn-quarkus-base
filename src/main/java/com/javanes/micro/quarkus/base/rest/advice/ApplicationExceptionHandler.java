package com.javanes.micro.quarkus.base.rest.advice;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javanes.micro.quarkus.base.exception.ApplicationException;
import com.javanes.micro.quarkus.base.rest.pojo.ExceptionResponse;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {

    final static Logger LOG = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @Override
    public Response toResponse(ApplicationException exception) {
        LOG.warn("UNHANDLED_CASE", exception);
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(exception.getCode().getCode());
        response.setMessage(exception.getMessage());
        return Response.status(exception.getCode().getHttpCode()).entity(response).build();
    }
    
}
