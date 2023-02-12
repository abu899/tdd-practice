package tdd.practice.productorderservice.payment;

import tdd.practice.productorderservice.order.Order;

interface PaymentPort {
    Order getOrder(Long orderId);

    void save(Payment payment);

    void pay(int totalPrice, String cardNumber);
}
