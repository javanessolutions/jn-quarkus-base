package com.javanes.bcpl.sso.clientes.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javanes.bcpl.sso.clientes.pojo.HelloResponse;
import com.javanes.bcpl.sso.clientes.service.HelloService;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello-controller")
public class HelloContoller {
    
    @Inject
    HelloService helloService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public Response sayHello(){
        try{
            return Response.ok().entity(helloService.sayHello("world")).build();
        }catch(Exception e){
            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello/{name}")
    public HelloResponse sayHello(@PathParam String name){
        return helloService.sayHello(name);
    }
}
