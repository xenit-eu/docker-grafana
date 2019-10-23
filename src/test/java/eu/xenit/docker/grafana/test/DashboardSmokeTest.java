package eu.xenit.docker.grafana.test;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

class DashboardSmokeTest extends RestAssuredTest {

    @Test
    void smokeTestDashboard() {
        given()
                .log().ifValidationFails()
                .when()
                .get()
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }

}
