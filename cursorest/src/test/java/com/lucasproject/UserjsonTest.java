package com.lucasproject;

import java.util.Arrays;

import javax.swing.SizeRequirements;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.matchers.*;
import org.hamcrest.collection.*;
import org.junit.Assert;
import org.junit.internal.matchers.*;
import org.junit.Test;

import groovy.transform.TupleConstructor;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserjsonTest {

      @Test
        public void deveVerificarPrimeiroNivel(){

            RestAssured.given()
            .when()
            .get("https://restapi.wcaquino.me/users/1")
            .then()
            .statusCode(200)
            .body("id", Matchers.is(1))
            .body("name", Matchers.is("João da Silva"))
            .body("age", Matchers.greaterThan(18));
        
        }

        @Test
         public void deveVerificarPrimeiroNivelOutros(){
          Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");
        //path
        //System.out.println(response.path("id"));
        Assert.assertEquals(new Integer(1), response.path("id"));

        //jsonpath
        JsonPath jpath = new JsonPath(response.asString());
        Assert.assertEquals(1, jpath.getInt("id"));

        //ftom

        int id = JsonPath.from(response.asString()).getInt("id");
        Assert.assertEquals(1, id);

         }

         @Test
         public void deveVerificarSegundoNivel(){

          RestAssured.given()
          .when()
          .get("https://restapi.wcaquino.me/users/2")
          .then()
          .statusCode(200)
          .body("name", Matchers.is("Maria Joaquina"))
          .body("endereco.rua", Matchers.is("Rua dos bobos"));

         }

         @Test
         public void deveVerificarLista(){
          RestAssured.given()
          .when()
          .get("https://restapi.wcaquino.me/users/3")
          .then()
          .statusCode(200)
          .body("name", Matchers.is("Ana Júlia"))
          .body("filhos", Matchers.hasSize(2))
          .body("filhos[0].name", Matchers.is("Zezinho"))
          .body("filhos[1].name", Matchers.is("Luizinho"))
          .body("filhos.name", Matchers.hasItems("Zezinho", "Luizinho")); 

         }

         @Test
         public void deveRetornarErroUsuarioInexistente(){

          RestAssured.given()
          .when()
          .get("https://restapi.wcaquino.me/users/4")
          .then()
          .statusCode(404)
          .body("error",  Matchers.is("Usuário inexistente"));

         }
         @Test
         public void deveVerificarListaRaiz(){
          RestAssured.given()
          .when()
          .get("https://restapi.wcaquino.me/users")
          .then()
          .statusCode(200)
          .body("name", Matchers.hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"))
          .body("age[1]", Matchers.is(25))
          .body(("filhos.name"), Matchers.hasItems(Arrays.asList("Zezinho", "Luizinho")));                 

         }

         @Test
         public void devoFazerVerificacoesAvancadas(){

          RestAssured.given()
          .when()
          .get("https://restapi.wcaquino.me/users")
          .then()
          .statusCode(200)
          .body("$full", Matchers.hasSize(3))
          .body("age.findAll{it <= 25}.size()", Matchers.is(2))
          .body("age.findAll{it <= 25 && it > 20}.size()", Matchers.is(1))
          .body("findAll{it.age <= 25 && it.age > 20}.name", Matchers.hasItem("Maria Joaquina"))
          .body("findAll{it.age <= 25 && it.age > 20}.name", Matchers.hasItem("Maria Joaquina"));


         }

}
