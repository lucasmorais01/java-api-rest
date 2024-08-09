package com.lucasproject;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class Schematest {
    @Test
    public void deveValidarSchemaXML(){

        RestAssured.given()
        .log().all()


        .when()
        .log().all()
        .get("https://restapi.wcaquino.me/usersXML")


        .then()
        .log().all()
        .statusCode(200)
        .body(RestAssuredMatchers.matchesXsdInClasspath("users.xsd"));
    }

    @Test
    public void deveValidarSchemaJson(){

        RestAssured.given()
        .log().all()


        .when()
        .log().all()
        .get("https://restapi.wcaquino.me/usersXML")


        .then()
        .log().all()
        .statusCode(200)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json"));
    }
}
