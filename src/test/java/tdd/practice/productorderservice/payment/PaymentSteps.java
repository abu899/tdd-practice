package tdd.practice.productorderservice.payment;

public class PaymentSteps {
    public static PaymentRequest createPaymentRequest() {
        Long orderId = 1L;
        String cardNumber = "1234";
        return new PaymentRequest(orderId, cardNumber);
    }
}