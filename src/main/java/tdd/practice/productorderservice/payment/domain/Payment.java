package tdd.practice.productorderservice.payment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import tdd.practice.productorderservice.order.domain.Order;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    private String cardNumber;

    public Payment(Order order, String cardNumber) {
        Assert.notNull(order, "주문 필수");
        Assert.hasText(cardNumber, "카드 번호 필수");
        this.order = order;
        this.cardNumber = cardNumber;
    }

    public int getPrice() {
        return order.getTotalPrice();
    }
}
