package tdd.practice.productorderservice.payment;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import tdd.practice.productorderservice.ApiTest;
import tdd.practice.productorderservice.order.OrderSteps;
import tdd.practice.productorderservice.payment.application.service.PaymentRequest;
import tdd.practice.productorderservice.product.ProductSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentApiTest extends ApiTest {

    @Test
    void paymentProduct() {
        ProductSteps.makeProductRequest();
        OrderSteps.createOrderProductRequest();
        PaymentRequest request = PaymentSteps.createPayment();

        ExtractableResponse<Response> response = PaymentSteps.createPaymentRequest(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
