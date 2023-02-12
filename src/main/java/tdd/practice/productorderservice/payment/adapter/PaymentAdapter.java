package tdd.practice.productorderservice.payment.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tdd.practice.productorderservice.order.domain.Order;
import tdd.practice.productorderservice.order.adapter.OrderRepository;
import tdd.practice.productorderservice.payment.application.port.PaymentPort;
import tdd.practice.productorderservice.payment.domain.Payment;

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
