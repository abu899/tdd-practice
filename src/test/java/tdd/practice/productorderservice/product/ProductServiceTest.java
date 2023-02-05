package tdd.practice.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private ProductService productService;
    private ProductPort productPort;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productPort = new ProductAdapter(productRepository);
        productService = new ProductService(productPort);
    }

    @Test
    void registerProduct() {
        String name = "ProductName";
        int price = 1000;
        final AddProductRequest request = new AddProductRequest(name, price, DiscountPolicy.NONE);
        productService.addProduct(request);
    }

    private static class AddProductRequest {
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
            assertThat(name).isNotEmpty();
            assertThat(price).isGreaterThan(0);
            assertThat(discountPolicy).isNotNull();

        }
    }

    private enum DiscountPolicy {
        NONE
    }

    private class ProductService {
        private final ProductPort productPort;

        private ProductService(ProductPort productPort) {
            this.productPort = productPort;
        }

        public void addProduct(AddProductRequest request) {
            Product product = new Product(request.name, request.price, request.discountPolicy);
            productPort.save(product);
        }
    }

    private class Product {
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;
        private Long id;

        public Product(String name, int price, DiscountPolicy discountPolicy) {
            Assert.hasText(name, "상품명은 필수");
            Assert.isTrue(price > 0, "price > 0 이상");
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }

        public void assignId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private interface ProductPort {
        void save(Product product);
    }

    private class ProductAdapter implements ProductPort {
        private final ProductRepository productRepository;

        private ProductAdapter(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        @Override
        public void save(Product product) {
            productRepository.save(product);
        }
    }

    private class ProductRepository {

        private Long sequence = 0L;
        private final Map<Long, Product> persistence = new HashMap<>();

        public void save(Product product) {
            product.assignId(++sequence);
            persistence.put(product.getId(), product);
        }
    }
}
