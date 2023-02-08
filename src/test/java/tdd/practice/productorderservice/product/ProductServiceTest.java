package tdd.practice.productorderservice.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

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
