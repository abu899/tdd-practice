package tdd.practice.productorderservice.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import tdd.practice.productorderservice.product.application.port.ProductPort;
import tdd.practice.productorderservice.product.application.service.GetProductResponse;
import tdd.practice.productorderservice.product.application.service.ProductService;
import tdd.practice.productorderservice.product.application.service.UpdateProductRequest;
import tdd.practice.productorderservice.product.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void updateProduct() {
        // given
        productService.addProduct(ProductSteps.makeProduct());
        Long productId = 1L;

        //when
        UpdateProductRequest request = ProductSteps.updateProductRequest();
        productService.updateProduct(productId, request);

        //then
        ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
        GetProductResponse responseBody = response.getBody();
        assertThat(responseBody.getName()).isEqualTo("Update Product");
        assertThat(responseBody.getPrice()).isEqualTo(2000);
    }

    private static class StubProductPort implements ProductPort {
        private final Product product;

        public StubProductPort(Product product) {
            this.product = product;
        }

        @Override
        public void save(Product product) {

        }

        @Override
        public Product getProduct(Long productId) {
            return product;
        }
    }
}
