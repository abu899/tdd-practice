package tdd.practice.productorderservice.order.adapter;

import org.springframework.stereotype.Component;
import tdd.practice.productorderservice.order.application.port.OrderPort;
import tdd.practice.productorderservice.order.domain.Order;
import tdd.practice.productorderservice.product.domain.Product;
import tdd.practice.productorderservice.product.adapter.ProductRepository;

@Component
class OrderAdapter implements OrderPort {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;


    public OrderAdapter(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않음"));
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
