package tdd.practice.productorderservice.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tdd.practice.productorderservice.product.ProductService;
import tdd.practice.productorderservice.product.ProductSteps;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Test
    void orderProduct() {
        productService.addProduct(ProductSteps.makeProduct());
        CreateOrderRequest request = createOrderProductRequest();

        orderService.createOrder(request);
    }

    private CreateOrderRequest createOrderProductRequest() {
        Long productId = 1L;
        int quantity = 2;
        CreateOrderRequest request = new CreateOrderRequest(productId, quantity);
        return request;
    }

}
