package tdd.practice.productorderservice.product.application.port;

import tdd.practice.productorderservice.product.domain.Product;

public interface ProductPort {
    void save(Product product);

    Product getProduct(Long productId);
}
