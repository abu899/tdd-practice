package tdd.practice.productorderservice.payment;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;
import tdd.practice.productorderservice.payment.application.service.PaymentRequest;

public class PaymentSteps {
    public static PaymentRequest createPayment() {
        Long orderId = 1L;
        String cardNumber = "1234";
        return new PaymentRequest(orderId, cardNumber);
    }

    public static ExtractableResponse<Response> createPaymentRequest(PaymentRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/payments")
                .then().log().all()
                .extract();
    }
}