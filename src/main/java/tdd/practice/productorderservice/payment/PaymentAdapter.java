package tdd.practice.productorderservice.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tdd.practice.productorderservice.order.Order;
import tdd.practice.productorderservice.order.OrderRepository;

@Component
@RequiredArgsConstructor
class PaymentAdapter implements PaymentPort {
    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않음"));
    }

    @Override
    public void pay(int totalPrice, String cardNumber) {
        paymentGateway.execute(totalPrice, cardNumber);
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}
