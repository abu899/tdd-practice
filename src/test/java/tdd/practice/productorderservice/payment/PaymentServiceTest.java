package tdd.practice.productorderservice.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tdd.practice.productorderservice.order.OrderService;
import tdd.practice.productorderservice.order.OrderSteps;
import tdd.practice.productorderservice.product.ProductService;
import tdd.practice.productorderservice.product.ProductSteps;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @Test
    void paymentProduct() {
        productService.addProduct(ProductSteps.makeProduct());
        orderService.createOrder(OrderSteps.createOrderProduct());
        PaymentRequest request = PaymentSteps.createPayment();

        paymentService.payment(request);
    }
}
