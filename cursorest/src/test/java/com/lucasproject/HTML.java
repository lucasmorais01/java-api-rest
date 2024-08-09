package com.lucasproject;

import org.hamcrest.Matchers.*;
import org.hamcrest.xml.HasXPath;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class HTML {

    @Test
    public void deveFazerBuscasComHTML(){

        RestAssured.given()
        .log().all()
        .when()
            .get("https://restapi.wcaquino.me/v2/users")

        .then()
        .log().all()
        .statusCode(200)
        .contentType(ContentType.HTML)
        .body("html.body.div.table.tbody.tr.size()", Matchers.is(3))
        .body("html.body.div.table.tbody.tr[1].td[2]", Matchers.is("25"));



        
        ;

        
    }

    
    @Test
    public void deveFazerBuscasComXpathHTML(){

        RestAssured.given()
        .log().all()
        .when()
            .get("https://restapi.wcaquino.me/v2/users")

        .then()
        .log().all()
        .statusCode(200)
        .contentType(ContentType.HTML)
        //.body(HasXPath("count(//table/tr)", Matchers.is("4")))
       
        


        
        ;

        
    }


    }


