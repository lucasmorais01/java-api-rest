package com.lucasproject;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class EnvioDadosTest {
 
    @Test  //VERIFICAR SE OS DADOS SAO NO FORMATO XML
    public void deveEnviarValorViaQuery(){

        RestAssured.given()
        .log().all()

        .when()
        .get("https://restapi.wcaquino.me/v2/users?format=xml")


        .then()
        .log().all()
        .statusCode(200)
        .contentType(ContentType.XML);
    }

    @Test // VERIFICAR SE OS DADOS SAO NO FORMATO JSON
    public void deveEnviarValorViaQueryJSON(){

        RestAssured.given()
        .log().all()

        .when()
        .get("https://restapi.wcaquino.me/v2/users?format=json")


        .then()
        .log().all()
        .statusCode(200)
        .contentType(ContentType.JSON);
    }

    @Test // VERIFICAR SE OS DADOS SAO NO FORMATO JSON
    public void deveEnviarValorViaQueryParam(){

        RestAssured.given()
        .log().all()
        .queryParam("format", "xml")

        .when()
        .get("https://restapi.wcaquino.me/v2/users")


        .then()
        .log().all()
        .statusCode(200)
        .contentType(ContentType.XML);
       
    } 
    
    @Test // VERIFICAR SE OS DADOS SAO NO FORMATO html
    public void deveEnviarValorViaHeader(){

        RestAssured.given()
        .log().all()
        .accept((ContentType.XML))

        .when()
        .get("https://restapi.wcaquino.me/v2/users")


        .then()
        .log().all()
        .statusCode(200)
        .contentType(ContentType.XML);
    }

}
