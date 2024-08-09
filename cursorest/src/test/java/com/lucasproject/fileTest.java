package com.lucasproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.RestAssured;

public class fileTest {

    @Test
    public void deveObrigadorEnviarArquivo(){

        RestAssured.given()
        .log().all()
       
       
        .when()
        .post("http://restapi.wcaquino.me/upload")


        .then()
        .log().all()
        .statusCode(404)
        .body("error", Matchers.is("Arquivo não enviado"));
    }

    @Test
    public void deveOFazerUploadArquivo(){

        RestAssured.given()
        .log().all()
        .multiPart("arquivo", new File("src/main/teste.txt"))
       
       
        .when()
        .post("http://restapi.wcaquino.me/upload")


        .then()
        .log().all()
        .statusCode(200)
        .body("error", Matchers.is("Arquivo não enviado"));
    }

    @Test
    public void deveBaixarArquivo(){
       
        RestAssured.given()
        .log().all()

        .when()
        .get("http://restapi.wcaquino.me/download")


        .then()
        .log().all()
        .statusCode(200)
        .extract().asByteArray();
        
        ;
        File imagem = new File("src/main/");

        OutputStream out = new FileOutputStream(imagem);
        out.write(image);
        out.close();
    }


}
