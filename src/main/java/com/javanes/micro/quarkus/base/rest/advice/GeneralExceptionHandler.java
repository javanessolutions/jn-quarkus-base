package com.javanes.micro.quarkus.base.rest.advice;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.javanes.micro.quarkus.base.rest.pojo.ExceptionResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class GeneralExceptionHandler implements ExceptionMapper<RuntimeException> {

    final static Logger LOG = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @Override
    public Response toResponse(RuntimeException exception) {
        LOG.warn("UNHANDLED_CASE", exception);
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(0);
        response.setMessage("Caso de uso no contemplado por el servidor. Contacte al administrador.");
        return Response.serverError().entity(response).build();
    }
    
}
