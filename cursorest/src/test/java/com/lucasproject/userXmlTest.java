package com.lucasproject;
import java.util.Arrays;

import javax.swing.SizeRequirements;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.matchers.*;

import io.restassured.RestAssured;

import org.hamcrest.collection.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.internal.matchers.*;
import org.junit.Test;


public class userXmlTest {

    @Test
     public void devoTrabalharcomXML() {

        RestAssured.given()
        .when()

        .get("https://restapi.wcaquino.me/usersXML/3")
        .then()
        .statusCode(200)
        .body("user.name", Matchers.is("Ana Julia"))
        .body("user.@id", Matchers.is("3"))
        .body("user.filhos.name[0]", Matchers.is("Zezinho"))
        .body("user.filhos.name[1]", Matchers.is("Luizinho"))
        .body("user.filhos.name", Matchers.hasItems("Luizinho", "Zezinho"))
        .body("user.age", Matchers.is("20"));

        
     }
     @Test
//trabalhando com ROOTPATH
     public void devoTrabalharcomXMLroot() {

        RestAssured.given()
        .when()

        .get("https://restapi.wcaquino.me/usersXML/3")
        .then()
        .statusCode(200)
        .rootPath("user")
        .body("name", Matchers.is("Ana Julia"))
        .body("@id", Matchers.is("3"))

        .rootPath("user.filhos")
        .body("name[0]", Matchers.is("Zezinho"))

        .detachRootPath("filhos")
        .body("filhos.name[1]", Matchers.is("Luizinho"))

        .appendRootPath("filhos")
        .body("name", Matchers.hasItems("Luizinho", "Zezinho"))

        .detachRootPath("filhos")
        .body("age", Matchers.is("20"));

}


@BeforeClass
public static void setup() {

    RestAssured.baseURI = "https://restapi.wcaquino.me";
    //  RestAssured.port = 80;

}

@Test
//trabalhando com XML
     public void devoFazerPesquisasAvancadasComXML() {
        RestAssured.baseURI = "https://restapi.wcaquino.me";
      //  RestAssured.port = 80;

        RestAssured.given()
        .when()

        .get("https://restapi.wcaquino.me/usersXML")
        .then()
        .statusCode(200)
        .body("users.user.size()", Matchers.is(3))
        .body("users.user.@id", Matchers.hasItems("1", "2","3"))
        .body("users.user.find{it.age == 25}.name", Matchers.is("Maria Joaquina"));

}


}

