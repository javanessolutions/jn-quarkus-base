package com.javanes.micro.quarkus.base.service;

import com.javanes.micro.quarkus.base.rest.pojo.HelloRequest;
import com.javanes.micro.quarkus.base.rest.pojo.HelloResponse;

public interface HelloService {
    
    public HelloResponse sayHello(final String name);

	public void saveHello(HelloRequest body);    
}
