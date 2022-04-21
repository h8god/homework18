import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiTestsShop {

    @Test
    void subscribe() {
        given()
                .formParam("email", "test@qa.guru")
                .when()
                .post("http://demowebshop.tricentis.com/subscribenewsletter")
                .then()
                .statusCode(200)
                .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."))
                .body("Success", is(true));
    }

    @Test
    public void noRegisteredVote() {

        given()
                .formParam("pollAnswerId", "2")
                .when()
                .post("http://demowebshop.tricentis.com/poll/vote")
                .then()
                .statusCode(200)
                .body("error", is("Only registered users can vote."));

    }
}
