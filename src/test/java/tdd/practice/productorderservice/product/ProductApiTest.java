package tdd.practice.productorderservice.product;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tdd.practice.productorderservice.ApiTest;
import tdd.practice.productorderservice.DatabaseCleanUp;

class ProductApiTest extends ApiTest {

    @Autowired
    private DatabaseCleanUp databaseCleanUp;

    @Test
    void registerProduct() {
        AddProductRequest request = makeProductRequest();
        // API 요청
        ExtractableResponse<Response> response = makeProductApiRequest(request);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> makeProductApiRequest(AddProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();
    }

    private AddProductRequest makeProductRequest() {
        String name = "ProductName";
        int price = 1000;
        DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new AddProductRequest(name, price, discountPolicy);
    }

}
