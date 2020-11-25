package com.javanes.bcpl.sso.clientes.service;

import javax.enterprise.context.ApplicationScoped;

import com.javanes.bcpl.sso.clientes.pojo.HelloResponse;

import org.jboss.logging.Logger;

@ApplicationScoped
public class HelloService {

    private static final Logger LOG = Logger.getLogger(HelloService.class);

    public HelloResponse sayHello(final String name){
        HelloResponse response = new HelloResponse();
        LOG.debug(String.format("CALL[sayHello]: %s", name));
        response.setResponse(String.format("Hello %s !!", name));
        return response;
    }
}
