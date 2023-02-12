package tdd.practice.productorderservice.order;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;
import tdd.practice.productorderservice.order.application.service.CreateOrderRequest;

public class OrderSteps {
    public OrderSteps() {
    }

    public static CreateOrderRequest createOrderProduct() {
        Long productId = 1L;
        int quantity = 2;
        return new CreateOrderRequest(productId, quantity);
    }

    public static ExtractableResponse<Response> createOrderProductRequest() {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createOrderProduct())
                .when()
                .post("/orders")
                .then()
                .log().all().extract();
    }
}