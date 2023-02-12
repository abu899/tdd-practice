package tdd.practice.productorderservice.payment.adapter;

import org.springframework.stereotype.Component;

@Component
public class ConsolePaymentGateway implements PaymentGateway {
    @Override
    public void execute(int totalPrice, String cardNumber) {
        System.out.println("Success Payment totalPrice = " + totalPrice);
    }
}
