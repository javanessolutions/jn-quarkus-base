package com.javanes.micro.quarkus.base.rest.controller;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Header;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.UUID;

@QuarkusTest
public class HelloControllerTest {
    
    @Test
    public void helloEndpointTestV1(){
        UUID uuid = UUID.randomUUID();
        given()
            .when()
                .header("exchangeId", uuid)
                .get("/v1/hello-controller/hello")
            .then()
                .statusCode(200)
                .body(is("{\"response\":\"Hello world !!\"}"));

        given()
            .when()
                .header("exchangeId", uuid)
                .get("/v1/hello-controller/hello/Alejandro")
            .then()
                .statusCode(200)
                .body(is("{\"response\":\"Hello Alejandro !!\"}"));
        
        given()
            .when()
                .header("exchangeId", uuid)
                .header(new Header("Content-Type", "application/json"))
                .body("{\"name\":\"Alejandro\"}")
                .post("/v1/hello-controller/hello")
            .then()
                .statusCode(200)
                .body(is("{\"response\":\"Hello Alejandro !!\"}"));        
    }

    @Test
    public void helloEndpointTestV2(){
        UUID uuid = UUID.randomUUID();
        given()
            .when()
                .header("exchangeId", uuid)
                .get("/v2/hello-controller/hello")
            .then()
                .statusCode(200)
                .body(is("{\"response\":\"Hola Pluton !!\"}"));

        given()
            .when()
                .header("exchangeId", uuid)
                .get("/v2/hello-controller/hello/Alejandro")
            .then()
                .statusCode(200)
                .body(is("{\"response\":\"Hola Alejandro !!\"}"));
        
        given()
            .when()
                .header("exchangeId", uuid)
                .header(new Header("Content-Type", "application/json"))
                .body("{\"name\":\"Alejandro\"}")
                .post("/v2/hello-controller/hello")
            .then()
                .statusCode(501)
                .body(notNullValue());        
    }


}
