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
    
    @Test
    public void helloEndpointTestV1(){
        given()
            .header(new Header("exchangeId", "1"))
            .when().get("/v1/hello-controller/hello")
            .then()
                .statusCode(200)
                .body(is("{\"response\":\"Hello world !!\"}"));
    }

    @Test
    public void helloNameEndpointTestV1(){
        String uuid = UUID.randomUUID().toString();
        given()
            .header(new Header("exchangeId", "1"))
            .pathParam("name", uuid)
            .when().get("/v1/hello-controller/hello/{name}")
            .then()
                .statusCode(200)
                .body(is(String.format("{\"response\":\"Hello %s !!\"}",uuid)));
    }

    @Test
    public void helloEndpointTestV2(){
        given()
            .header(new Header("exchangeId", "1"))
            .when().get("/v2/hello-controller/hello")
            .then()
                .statusCode(200)
                .body(is("{\"response\":\"Hola Pluton !!\"}"));
    }

    @Test
    public void helloNameEndpointTestV2(){
        String uuid = UUID.randomUUID().toString();
        given()
            .header(new Header("exchangeId", "1"))
            .pathParam("name", uuid)
            .when().get("/v2/hello-controller/hello/{name}")
            .then()
                .statusCode(200)
                .body(is(String.format("{\"response\":\"Hola %s !!\"}",uuid)));
    }

}
