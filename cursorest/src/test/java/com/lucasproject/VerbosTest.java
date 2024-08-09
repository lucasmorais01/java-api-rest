package com.lucasproject;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class VerbosTest {

//TESTE SALVANDO NOVO USUARIO
    @Test
    public void deveSalvarNovoUsuario(){
       
        RestAssured.given()
        .log().all()
        .contentType("application/json")
        .body("{\"name\": \"Lucas\",\"age\": 30, \"salary\": 9000}")
       
       
        .when()
        .post("https://restapi.wcaquino.me/users")


        .then()
        .log().all()
        .statusCode(201)
        .body("id", Matchers.notNullValue())
        .body("name", Matchers.is("Lucas"))
        .body("age", Matchers.is(30));

}

// TESTE NEGATIVO - NAO PODE SALVAR SEM NOME
@Test
public void naoDeveSalvarUsuarioSemNome(){

    RestAssured.given()
        .log().all()
        .contentType("application/json")
        .body("{\"age\": 30, \"salary\": 9000}")
       
       
        .when()
        .post("https://restapi.wcaquino.me/users")


        .then()
        .log().all()
        .statusCode(400)
        .body("id", Matchers.nullValue())
        .body("error", Matchers.is("Name é um atributo obrigatório"));

}
//CADASTRAR NOVO USUARIO UTILIZANDO XML
@Test
public void deveSalvarNovoUsuarioXML(){ 
   
    RestAssured.given()
    .log().all()
    .contentType(ContentType.XML)
    .body("<user><name>Joao</name><age>30</age></user>")
   
   
    .when()
    .post("https://restapi.wcaquino.me/usersXML")


    .then()
    .log().all()
    .statusCode(201)
    .body("user.@id", Matchers.notNullValue())
    .body("user.name", Matchers.is("Joao"))
    .body("user.age", Matchers.is("30"));

}

@Test
    public void devoAlterarUsuario(){
       
        RestAssured.given()
        .log().all()
        .contentType(ContentType.JSON)
        .body("{\"name\": \"Lucas\",\"age\": 30, \"salary\": 500}")
       
       
        .when()
        .put("https://restapi.wcaquino.me/users/1")


        .then()
        .log().all()
        .statusCode(200) // status  code 200 sempre que for atualizar dados
        .body("id", Matchers.notNullValue())
        .body("name", Matchers.is("Lucas"))
        .body("age", Matchers.is(30))
        .body("salary", Matchers.is(500));


}
@Test
    public void devoCustomizarURL(){
       
        RestAssured.given()
        .log().all()
        .contentType(ContentType.JSON)
        .body("{\"name\": \"Lucas\",\"age\": 30, \"salary\": 500}")
       
       
        .when()
        .put("https://restapi.wcaquino.me/{entidade}/{userId}", "users", "1")
        .then()
        .log().all()
        .statusCode(200) // status  code 200 sempre que for atualizar dados
        .body("id", Matchers.notNullValue())
        .body("name", Matchers.is("Lucas"))
        .body("age", Matchers.is(30))
        .body("salary", Matchers.is(500));

}

@Test
    public void devoCustomizarURLParte2(){
       
        RestAssured.given()
        .log().all()
        .contentType(ContentType.JSON)
        .body("{\"name\": \"Lucas\",\"age\": 30, \"salary\": 500}")
        .pathParam("entidade", "users")
        .pathParam("userId", "1")

       
       
        .when()
        .put("https://restapi.wcaquino.me/{entidade}/{userId}")
        .then()
        .log().all()
        .statusCode(200) // status  code 200 sempre que for atualizar dados
        .body("id", Matchers.notNullValue())
        .body("name", Matchers.is("Lucas"))
        .body("age", Matchers.is(30))
        .body("salary", Matchers.is(500));


}
      @Test  //TESTE DE DELETAR USUARIO
      public void DeveRemoverUsuario() {  
        RestAssured.given()
        .log().all()

        .when()
            .delete("https://restapi.wcaquino.me/users/1")

        .then()
        .statusCode(204)
        .log().all();
      }

      @Test  //TESTE DE DELETAR USUARIO
      public void NaoDeveRemoverUsuario() {  
        RestAssured.given()
        .log().all()

        .when()
            .delete("https://restapi.wcaquino.me/users/1000")

        .then()
        .statusCode(400)
        .body("error", Matchers.is("Registro inexistente"))
        .log().all();
      }

      @Test
      public void devoSalvarUsuarioUsandoMap(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "Usuario via map");
        params.put("age", 25);

        RestAssured.given()
        .log().all()
        .contentType(ContentType.JSON)
        .body(params)

        .when()
           
        .then()
        .statusCode(201)
        .body("id", Matchers.notNullValue())
        .body("name", Matchers.is("Usuario via map"))
        .body("age", Matchers.is(25))
       .log().all();

      }

      @Test
      public void devoSalvarUsuarioUsandoObjeto(){

        User user = new User("Usuario via objeto", 35);


        RestAssured.given()
        .log().all()
        .contentType(ContentType.JSON)
        .body(user)

        .when()
           
        .then()
        .log().all()
        .statusCode(201)
        .body("id", Matchers.notNullValue())
        .body("name", Matchers.is("Usuario via objeto"))
        .body("age", Matchers.is(35))
       .log().all();

      }

      //@Test
      //public void devoeDeserializarObjetoSalvarUsuario(){
        //User user = new User("Usuario deserializado", 35);
    
        //User usuarioInserido =  RestAssured.given()

        //.log().all()
        //.contentType(ContentType.JSON)
        //.body(user)

        //.when()
        //.post("https://restapi.wcaquino.me/users")
           
        //.then()
        //.log().all()
        //.statusCode(201)
        //.extract().body().as(User.class);

      //  System.out.println(usuarioInserido);
     //   Assert.assertThat(usuarioInserido.getId(), Matchers.notNullValue());
      //  Assert.assertEquals("Usuario deserializado", usuarioInserido.getName());
     //   Assert.assertThat(usuarioInserido.getAge(), Matchers.is(35));

     // }

     


}