package com.lucasproject;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class olaMundoTest {

    @Test
    public void testOlaMundo() {
    
      Response response =  RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
      Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
      Assert.assertTrue(response.statusCode() == 200);

      ValidatableResponse validacao = response.then();
      validacao.statusCode(200);
        }

    @Test
    public void devoConhecerOutrasFormasRestAssured(){
      Response response =  RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
    
      ValidatableResponse validacao = response.then();
      validacao.statusCode(200);

      RestAssured.get("https://restapi.wcaquino.me/ola").then().statusCode(200);

      RestAssured.given()
           ///pré condição
      .when()
          //ação
      .get("https://restapi.wcaquino.me/ola")
      .then()
         //verificação
      .statusCode(200);
  
    }

     @Test public void devoConhecerMathersHamcrest(){
          Assert.assertThat("Maria", Matchers.is("Maria"));
          Assert.assertThat(128, Matchers.isA(Integer.class));
          Assert.assertThat(128d, Matchers.isA((Double.class)));
          Assert.assertThat(128d, Matchers.greaterThan(128d));
          Assert.assertThat(130d, Matchers.lessThanOrEqualTo(130d));
     }

     
     @Test
     public void devoValidarBody(){
      RestAssured.given()
           ///pré condição
      .when()
          //ação
      .get("https://restapi.wcaquino.me/ola")
      .then()
         //verificação
      .statusCode(200)
      
     }

}
