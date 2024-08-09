package com.lucasproject;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class authTest {

      @Test // chave API KEy 4c0bb848b2a52c05d1474d6e35c17a43

    public void deveAcessarSWAPI(){
        RestAssured.given() // DADO QUE
        .log().all()


        .when() // QUANDO ACESSAR
            .get("https://swapi.dev/api/people/1")
        
        .then() // ENTAO
        .log().all()
        .statusCode(200)
        .body("name", Matchers.is("Luke Skywalker"));
    }

    @Test
    public void deveObterCLima(){

        RestAssured.given() // DADO QUE
        .log().all()
        .queryParam("q", "Recife")
        .queryParam("appid", "4c0bb848b2a52c05d1474d6e35c17a43")
        .queryParam("units", "metric")

        .when() // QUANDO ACESSAR
            .get("https://api.openweathermap.org/data/2.5/weather")
        .then() // ENTAO
        .log().all()
        .statusCode(200)
        .body("name", Matchers.is("Recife"))
        .body("main.temp", Matchers.greaterThan(16.29f))
        .body("weather[0].description", Matchers.is("scattered clouds"))
        .body("sys.country", Matchers.is("BR"));

    }

    @Test
    public void naoDeveAcessarSemSenha(){
        RestAssured.given()
        .log().all()

        .when()
        .get("https://restapi.wcaquino.me/BASICAUTH")

        .then()
        .log().all()
        .statusCode(401);
    }

    @Test
    public void deveFazerAuthBasic(){
        RestAssured.given()
        .log().all()

        .when()
        .get("https://admin:senha@restapi.wcaquino.me/BASICAUTH") // passando a senha na URL

        .then()
        .log().all()
        .statusCode(200)
        .body("status", Matchers.is("logado"));
    }

    @Test
    public void deveFazerAuthBasic2(){
        RestAssured.given()
        .log().all()
        .auth().basic("admin", "senha")  //passando a autenticacao de usuario e senha no Given
        

        .when()
        .get("https://restapi.wcaquino.me/BASICAUTH")

        .then()
        .log().all()
        .statusCode(200)
        .body("status", Matchers.is("logado"));
    }

    @Test
    public void deveFazerAuthBasicChallenge(){
        RestAssured.given()
        .log().all()
        .auth().preemptive().basic("admin", "senha")
        

        .when()
        .get("https://restapi.wcaquino.me/BASICAUTH2")

        .then()
        .log().all()
        .statusCode(200)
        .body("status", Matchers.is("logado"));
    }

    @Test
    public void deveFazerAutenticacaoComTokenJWT(){
        Map<String, String> login = new HashMap<String, String>();
        login.put("email", "lucasgabrielsm7@gmail.com");
        login.put("senha", "teste123");
        

        //login na api

        // receber token

        //obter contas
        RestAssured.given()
        

        .log().all()
        .body(login)
        .contentType(ContentType.JSON)
        

        .when()
        .post("https://barrigarest.wcaquino.me/signin")
        

        .then()
        .log().all()
        .statusCode(200)
        .extract().path("token")
        ;


        
        
        login.put("email", "lucasgabrielsm7@gmail.com");
        login.put("senha", "teste123");
        RestAssured.given()
    
  
        

    .log().all()
    

    .when()
    .get("https://barrigarest.wcaquino.me/contas")
    

    .then()
    .log().all();  
    
    }
    @Test
    public void deveAcessarAplicacaoWeb(){

        //login
        RestAssured.given()
        .log().all()
        .formParam("email", "lucasgabrielsm7@gmail.com")
        .formParam("senha", "teste123")
        .contentType(ContentType.URLENC.withCharset("UTF-8"))

        .when()
        .post("https://seubarriga.wcaquino.me/logar")

        .then()
        .log().all()
        .statusCode(200);

        //obter conta


    }

}





