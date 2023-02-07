package tdd.practice.productorderservice.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceTest {

    private ProductService productService;
    private ProductPort productPort;

    @Test
    void updateProduct() {
        // given
        Product product = new Product("Product", 1000, DiscountPolicy.NONE);
        productPort = new StubProductPort(product);
        productService = new ProductService(productPort);
        Long productId = 1L;

        //when
        UpdateProductRequest request = new UpdateProductRequest("Update Product", 2000, DiscountPolicy.NONE);
        productService.updateProduct(productId, request);

        //then
        assertThat(product.getName()).isEqualTo("Update Product");
        assertThat(product.getPrice()).isEqualTo(2000);
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
