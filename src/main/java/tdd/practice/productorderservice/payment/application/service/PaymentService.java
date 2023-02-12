package tdd.practice.productorderservice.payment.application.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdd.practice.productorderservice.order.domain.Order;
import tdd.practice.productorderservice.payment.application.port.PaymentPort;
import tdd.practice.productorderservice.payment.domain.Payment;

@RestController
@RequestMapping("/payments")
public
class PaymentService {
    private final PaymentPort paymentPort;

    public PaymentService(PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> payment(@RequestBody PaymentRequest request) {
        Order order = paymentPort.getOrder(request.getOrderId());
        Payment payment = new Payment(order, request.getCardNumber());
        paymentPort.pay(payment.getPrice(), payment.getCardNumber());
        paymentPort.save(payment);

        return ResponseEntity.ok().build();
    }
}
