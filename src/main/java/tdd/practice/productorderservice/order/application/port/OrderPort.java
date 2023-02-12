package tdd.practice.productorderservice.order.application.port;

import tdd.practice.productorderservice.order.domain.Order;
import tdd.practice.productorderservice.product.domain.Product;

public interface OrderPort {
    Product getProductById(Long productId);

    void save(Order order);
}
