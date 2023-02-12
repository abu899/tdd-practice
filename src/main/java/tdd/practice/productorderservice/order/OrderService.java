package tdd.practice.productorderservice.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdd.practice.productorderservice.product.Product;

@RestController
@RequestMapping("/orders")
public
class OrderService {
    private final OrderPort orderPort;

    public OrderService(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request) {
        Product product = orderPort.getProductById(request.getProductId());
        Order order = new Order(product, request.getQuantity());
        orderPort.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
