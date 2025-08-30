package com.example.digg.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CustomerControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void createCustomer_shouldReturn201_whenValidRequest() {
        final CreateCustomerRequest testTestsson = new CreateCustomerRequest(
                "Test testsson",
                "adress 123",
                "email@something.com",
                "+46123"
        );

        given()
                .contentType("application/json")
                .body(testTestsson)
                .when()
                .post("/v1/customer")
                .then()
                .statusCode(201);
    }

    @Test
    void createCustomer_shouldReturn400_whenInvalidRequest() {
        final CreateCustomerRequest testTestsson = new CreateCustomerRequest(
                "Test testsson",
                "adress 123",
                "invalidemail",
                "+46123"
        );

        given()
                .contentType("application/json")
                .body(testTestsson)
                .log().all()
                .when()
                .post("/v1/customer")
                .then()
                .statusCode(400);
    }

    @Test
    void getAllCustomers_shouldReturn200_whenValidRequest() {
        given()
                .when()
                .get("/v1/customer")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(15));
    }

    @Test
    void createCusterAndGetCustomer_fullFlow() {
        final CreateCustomerRequest testTestsson = new CreateCustomerRequest(
                "Test testsson",
                "adress 123",
                "email@something.com",
                "+46123"
        );

        final String customerId = given()
                .contentType("application/json")
                .body(testTestsson)
                .when()
                .post("/v1/customer")
                .then()
                .statusCode(201)
                .extract().header("CustomerId");

        given()
                .when()
                .get("/v1/customer")
                .then()
                .statusCode(200)
                .body("id", hasItem(customerId));
    }
}