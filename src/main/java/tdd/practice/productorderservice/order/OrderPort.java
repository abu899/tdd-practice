package tdd.practice.productorderservice.order;

import tdd.practice.productorderservice.product.Product;

interface OrderPort {
    Product getProductById(Long productId);

    void save(Order order);
}
