package com.javanes.micro.quarkus.base.rest.controller;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Header;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

import javax.inject.Inject;

import com.javanes.micro.quarkus.base.config.AppConfiguration;

@QuarkusTest
public class HelloControllerTest {
    
    @Inject
    AppConfiguration appConfiguration;

    @Test
    public void helloEndpointTest(){
        given()
            .header(new Header("exchangeId", "1"))
            .when().get("/v1/hello-controller/hello")
            .then()
                .statusCode(200)
                .body(is(String.format("{\"response\":\"%s %s %s\"}",appConfiguration.getGreeting(),appConfiguration.getDefaultName(),appConfiguration.getSufix())));
    }

    @Test
    public void helloNameEndpointTest(){
        String uuid = UUID.randomUUID().toString();
        given()
            .header(new Header("exchangeId", "1"))
            .pathParam("name", uuid)
            .when().get("/v1/hello-controller/hello/{name}")
            .then()
                .statusCode(200)
                .body(is(String.format("{\"response\":\"%s %s %s\"}",appConfiguration.getGreeting(),uuid,appConfiguration.getSufix())));
    }
}
