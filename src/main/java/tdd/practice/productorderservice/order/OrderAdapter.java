package tdd.practice.productorderservice.order;

import org.springframework.stereotype.Component;
import tdd.practice.productorderservice.product.Product;
import tdd.practice.productorderservice.product.ProductRepository;

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
