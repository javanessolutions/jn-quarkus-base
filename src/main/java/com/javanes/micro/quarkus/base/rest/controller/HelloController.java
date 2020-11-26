package com.javanes.micro.quarkus.base.rest.controller;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javanes.micro.quarkus.base.rest.pojo.HelloRequest;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v1/hello-controller")
public interface HelloController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public Response sayHello(@NotEmpty @HeaderParam String exchangeId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello/{name}")
    public Response sayHello(@NotEmpty @HeaderParam String exchangeId, @PathParam String name);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public Response saveHello(@NotEmpty String exchangeId, @RequestBody HelloRequest body);
}
