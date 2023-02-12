package tdd.practice.productorderservice.payment.adapter;

interface PaymentGateway {
    void execute(int totalPrice, String cardNumber);
}
