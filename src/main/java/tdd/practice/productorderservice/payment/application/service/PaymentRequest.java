package tdd.practice.productorderservice.payment.application.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor
public
class PaymentRequest {
    private Long orderId;
    private String cardNumber;

    public PaymentRequest(Long orderId, String cardNumber) {
        Assert.notNull(orderId, "주문 번호 필수");
        Assert.hasText(cardNumber, "카드번호 필수");
        this.orderId = orderId;
        this.cardNumber = cardNumber;
    }
}
