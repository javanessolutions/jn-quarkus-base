package com.javanes.bcpl.sso.clientes.controller;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

@QuarkusTest
public class HelloControllerTest {
    
    @Test
    public void helloEndpointTest(){
        given()
          .when().get("/hello-controller/hello")
          .then()
             .statusCode(200)
             .body(is("{\"response\":\"Hello world !!\"}"));    }

    @Test
    public void helloNameEndpointTest(){
        String uuid = UUID.randomUUID().toString();
        given()
            .pathParam("name", uuid)
            .when().get("/hello-controller/hello/{name}")
            .then()
                .statusCode(200)
                .body(is(String.format("{\"response\":\"Hello %s !!\"}",uuid)));
    }
}
