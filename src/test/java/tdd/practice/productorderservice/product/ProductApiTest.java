package tdd.practice.productorderservice.product;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import tdd.practice.productorderservice.ApiTest;
import tdd.practice.productorderservice.DatabaseCleanUp;

import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest {

    @Autowired
    private DatabaseCleanUp databaseCleanUp;

    @Test
    void registerProduct() {
        // API 요청
        ExtractableResponse<Response> response = ProductSteps.registerProduct();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void findProduct() {
        //상품 등록
        ProductSteps.registerProduct();
        Long productId = 1L;

        // API 요청
        ExtractableResponse<Response> response = ProductSteps.findProductRequest(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo("ProductName");
    }

}
