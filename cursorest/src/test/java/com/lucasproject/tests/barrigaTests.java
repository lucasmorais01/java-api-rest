package com.lucasproject.tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lucasproject.core.BaseTest;

import io.restassured.RestAssured;

public class barrigaTests extends BaseTest{
  
    @Test

    public void naoDeveAcessarAPISemtoken(){

        RestAssured.given()

        .when()
        .get("/contas")

        .then()
        .statusCode(401)  ;
    }

    @Test

    public void deveIncluirContascomSucesso(){
        Map<String, String> login = new HashMap<>();
        login.put("email", "lucasgabrielsm7@gmail.com");
        login.put("senha", "teste123");
        

       String token = RestAssured.given()
        
        .body(login)


        .when()
        .post("/signin")

        .then()
        .statusCode(200)
        .extract().path("token");

        RestAssured.given()
        .header("Authorization", "JWT" + token)
        .body("{\"nome\": \"contaTeste\"}")

        .when()
        .post("/contas")
        .then()
        .statusCode(201);


}

@Test

public void deveAlterarContacomSucesso(){
    Map<String, String> login = new HashMap<>();
    login.put("email", "lucasgabrielsm7@gmail.com");
    login.put("senha", "teste123");
    

   String token = RestAssured.given()
    
    .body(login)


    .when()
    .log().all()
    .post("/signin")

    .then()
    .statusCode(200)
    .extract().path("token");

    RestAssured.given()
    .header("Authorization", "JWT" + token)
    .body("{\"nome\": \"conta teste1\"}")

    .when()
    .post("/contas")
    .then()
    .statusCode(201);




}

}