package tdd.practice.productorderservice.order;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import tdd.practice.productorderservice.ApiTest;
import tdd.practice.productorderservice.product.ProductSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderApiTest extends ApiTest {

    private final OrderSteps orderSteps = new OrderSteps();

    @Test
    void orderProduct() {
        ProductSteps.makeProductRequest();
        CreateOrderRequest request = orderSteps.createOrderProduct();

        ExtractableResponse<Response> response = orderSteps.createOrderProductRequest(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }
}
