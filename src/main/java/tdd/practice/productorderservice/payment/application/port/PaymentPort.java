package tdd.practice.productorderservice.payment.application.port;

import tdd.practice.productorderservice.order.domain.Order;
import tdd.practice.productorderservice.payment.domain.Payment;

public interface PaymentPort {
    Order getOrder(Long orderId);

    void save(Payment payment);

    void pay(int totalPrice, String cardNumber);
}
