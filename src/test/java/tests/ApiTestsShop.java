package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static listeners.CustomAllureListener.withCustomTemplates;
import static org.hamcrest.Matchers.is;

public class ApiTestsShop {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        RestAssured.filters(withCustomTemplates());
    }


    @Test
    void subscribe() {
        given()
                .formParam("email", "test@qa.guru")
                .log().uri()
                .when()
                .post("/subscribenewsletter")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."))
                .body("Success", is(true));
    }

    @Test
    public void noRegisteredVote() {

        given()
                .formParam("pollAnswerId", "2")
                .when()
                .post("/poll/vote")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("error", is("Only registered users can vote."));

    }
}
