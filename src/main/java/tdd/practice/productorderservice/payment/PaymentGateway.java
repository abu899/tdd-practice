package tdd.practice.productorderservice.payment;

interface PaymentGateway {
    void execute(int totalPrice, String cardNumber);
}
