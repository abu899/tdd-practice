package tdd.practice.productorderservice.product;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tdd.practice.productorderservice.ApiTest;

import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest {

    @Autowired
    ProductRepository productRepository;

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

    @Test
    void updateProduct() {
        ProductSteps.registerProduct();
        Long productId = 1L;

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ProductSteps.updateProductRequest())
                .when()
                .patch("/products/{productId}", productId)
                .then()
                .log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(productRepository.findById(productId).get().getPrice()).isEqualTo(2000);
        assertThat(productRepository.findById(productId).get().getName()).isEqualTo("Update Product");
    }
}
