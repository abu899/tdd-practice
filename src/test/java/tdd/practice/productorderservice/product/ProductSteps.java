package tdd.practice.productorderservice.product;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class ProductSteps {
    public static AddProductRequest makeProduct() {
        String name = "ProductName";
        int price = 1000;
        DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new AddProductRequest(name, price, discountPolicy);
    }

    public static ExtractableResponse<Response> makeProductRequest() {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(makeProduct() )
                .when()
                .post("/products")
                .then()
                .log().all().extract();
    }

    public static ExtractableResponse<Response> findProductRequest(Long productId) {
        return RestAssured.given().log().all()
                .when()
                .get("/products/{productId}", productId)
                .then().log().all()
                .extract();
    }

    public static UpdateProductRequest updateProductRequest() {
        return new UpdateProductRequest("Update Product", 2000, DiscountPolicy.NONE);
    }
}