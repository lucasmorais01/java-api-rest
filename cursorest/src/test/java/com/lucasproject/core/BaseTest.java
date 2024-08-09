package com.lucasproject.core;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import com.lucasproject.core.Constantes;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseTest implements Constantes {
    @BeforeClass
    public  static void setup() {

        RestAssured.baseURI = APP_BASEURL;
        RestAssured.port = APP_PORT;
        RestAssured.basePath = APP_BASE_PATH;

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(APP_CONTENT_TYPE);

        RestAssured.requestSpecification = reqBuilder.build();

        io.restassured.builder.ResponseSpecBuilder resBuilder;
        Object ResponseSpecBuilder = resBuilder = new ResponseSpecBuilder();
        //resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        


    }

}
