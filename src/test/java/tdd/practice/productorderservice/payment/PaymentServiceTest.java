package tdd.practice.productorderservice.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentServiceTest {

    private PaymentService paymentService;
    private PaymentPort paymentPort;
    private PaymentGateway paymentGateway;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentGateway = new ConsolePaymentGateway();
        paymentRepository = new PaymentRepository();
        paymentPort = new PaymentAdapter(paymentGateway, paymentRepository);
        paymentService = new PaymentService(paymentPort);
    }

    @Test
    void paymentProduct() {
        PaymentRequest request = PaymentSteps.createPaymentRequest();
        paymentService.payment(request);
    }
}
