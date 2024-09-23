package testAPI.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

// класс с общими спецификациями запроса и ответа в тестах api
public class Spec {
    public static RequestSpecification requestSpec = with()
            .log().all();
    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();
}
