package tdd.practice.productorderservice.payment;

public class ConsolePaymentGateway implements PaymentGateway {
    @Override
    public void execute(int totalPrice, String cardNumber) {
        System.out.println("Success Payment totalPrice = " + totalPrice);
    }
}
