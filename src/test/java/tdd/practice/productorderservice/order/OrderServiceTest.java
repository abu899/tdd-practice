package tdd.practice.productorderservice.order;

import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import tdd.practice.productorderservice.product.DiscountPolicy;
import tdd.practice.productorderservice.product.Product;
import tdd.practice.productorderservice.product.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderPort orderPort;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
        orderPort = new OrderPort() {
            @Override
            public Product getProductById(Long productId) {
                return new Product("ProductName", 1000, DiscountPolicy.NONE);
            }

            @Override
            public void save(Order order) {
                orderRepository.save(order);

            }
        };
        orderService = new OrderService(orderPort);
    }

    @Test
    void orderProduct() {

        Long productId = 1L;
        int quantity = 2;
        CreateOrderRequest request = new CreateOrderRequest(productId, quantity);
        orderService.createOrder(request);
    }

    @Getter
    private class CreateOrderRequest {
        private final Long productId;
        private final int quantity;

        public CreateOrderRequest(Long productId, int quantity) {
            Assert.notNull(productId, "상품 id 는 필수");
            Assert.isTrue(quantity > 0, "상품은 1개 이상");
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    private class OrderService {
        private final OrderPort orderPort;

        public OrderService(OrderPort orderPort) {
            this.orderPort = orderPort;
        }

        public void createOrder(CreateOrderRequest request) {
            Product product = orderPort.getProductById(request.getProductId());
            Order order = new Order(product, request.getQuantity());
            orderPort.save(order);
        }

    }

    private class OrderAdapter implements OrderPort{

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

    @Getter
    private class Order {
        private Long id;
        private final Product product;
        private final int quantity;

        public Order(Product product, int quantity) {
            Assert.notNull(product, "상품 필수");
            Assert.isTrue(quantity > 0, "상품은 1개 이상");
            this.product = product;
            this.quantity = quantity;
        }

        public void assignId(Long id) {
            this.id = id;
        }
    }

    private class OrderRepository {
        private Map<Long, Order> persistence = new HashMap<>();
        private Long sequence = 0L;

        public void save(Order order) {
            order.assignId(++sequence);
            persistence.put(order.getId(), order);
        }
    }

    private interface OrderPort {
        Product getProductById(Long productId);

        void save(Order order);
    }
}
