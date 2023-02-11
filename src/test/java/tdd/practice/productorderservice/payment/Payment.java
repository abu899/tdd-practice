package tdd.practice.productorderservice.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import tdd.practice.productorderservice.order.Order;

@Getter
@NoArgsConstructor
class Payment {
    private Order order;
    private String cardNumber;
    private Long id;

    public Payment(Order order, String cardNumber) {
        Assert.notNull(order, "주문 필수");
        Assert.hasText(cardNumber, "카드 번호 필수");
        this.order = order;
        this.cardNumber = cardNumber;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return order.getTotalPrice();
    }
}
