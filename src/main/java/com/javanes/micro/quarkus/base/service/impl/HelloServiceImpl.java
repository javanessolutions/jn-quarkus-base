package com.javanes.micro.quarkus.base.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.javanes.micro.quarkus.base.config.AppConfiguration;
import com.javanes.micro.quarkus.base.exception.ApplicationException;
import com.javanes.micro.quarkus.base.rest.pojo.HelloRequest;
import com.javanes.micro.quarkus.base.rest.pojo.HelloResponse;
import com.javanes.micro.quarkus.base.service.HelloService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class HelloServiceImpl implements HelloService {

    private static final Logger LOG = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Inject
    AppConfiguration appConfiguration;

    public HelloResponse sayHello(final String name) throws ApplicationException{
        HelloResponse response = new HelloResponse();
        LOG.debug(String.format("CALL[sayHello]: %s", name));
        response.setResponse(
                String.format("%s %s %s", appConfiguration.getGreeting(), name, appConfiguration.getSufix()));
        return response;
    }

    public void saveHello(HelloRequest body) throws ApplicationException{
        throw new UnsupportedOperationException("No implementado");
    }
}
