package com.javanes.micro.quarkus.base.rest.controller.impl;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.javanes.micro.quarkus.base.config.AppConfiguration;
import com.javanes.micro.quarkus.base.exception.ApplicationException;
import com.javanes.micro.quarkus.base.rest.controller.HelloController;
import com.javanes.micro.quarkus.base.rest.pojo.HelloRequest;
import com.javanes.micro.quarkus.base.rest.pojo.HelloResponse;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/v2/hello-controller")
public class HelloContollerImplV2 implements HelloController {

    /**
     * Logger de la clase, en todos los casos se usará el de Jboss.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HelloContollerImplV2.class);

    @Inject
    AppConfiguration AppConfiguration;

    public Response sayHello(@NotEmpty @HeaderParam String exchangeId) throws ApplicationException{
        LOG.debug(String.format("EXCHANGE_ID: %s", exchangeId));
        //
        // En los metodos del controller no se pone lógica, unicamente sirven
        // como fachadas para los servicios.
        // - Siempre regresan una instancia de la clase "Response".
        // - Escoger el StatusCode de respuesta (200,201,204,400,406,409,etc).
        // Para ello la clase "Response" brinda facilidades.
        // - Cualquier excepción no controlada es manejada por:
        // @See GeneralExceptionHandler
        HelloResponse response = new HelloResponse();
        response.setResponse("Hello World V2");
        return Response.ok().entity(response).build();
    }

    public Response sayHello(@NotEmpty @HeaderParam String exchangeId, @PathParam String name) throws ApplicationException{
        LOG.debug(String.format("EXCHANGE_ID: %s", exchangeId));
        //
        // En los metodos del controller no se pone lógica, unicamente sirven
        // como fachadas para los servicios.
        // - Siempre regresan una instancia de la clase "Response".
        // - Escoger el StatusCode de respuesta (200,201,204,400,406,409,etc).
        // Para ello la clase "Response" brinda facilidades.
        // - Cualquier excepción no controlada es manejada por:
        // @See GeneralExceptionHandler
        HelloResponse response = new HelloResponse();
        response.setResponse(String.format("Hello %s V2",name));
        return Response.ok().entity(response).build();
    }

    public Response saveHello(@NotEmpty String exchangeId, HelloRequest body) throws ApplicationException{
        LOG.debug(String.format("EXCHANGE_ID: %s", exchangeId));
        //
        // En los metodos del controller no se pone lógica, unicamente sirven
        // como fachadas para los servicios.
        // - Siempre regresan una instancia de la clase "Response".
        // - Escoger el StatusCode de respuesta (200,201,204,400,406,409,etc).
        // Para ello la clase "Response" brinda facilidades.
        // - Cualquier excepción no controlada es manejada por:
        // @See GeneralExceptionHandler
        throw new ApplicationException();
    }

}
