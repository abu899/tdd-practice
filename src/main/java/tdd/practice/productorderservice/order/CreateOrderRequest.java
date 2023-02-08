package tdd.practice.productorderservice.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor
class CreateOrderRequest {
    private Long productId;
    private int quantity;

    public CreateOrderRequest(Long productId, int quantity) {
        Assert.notNull(productId, "상품 id 는 필수");
        Assert.isTrue(quantity > 0, "상품은 1개 이상");
        this.productId = productId;
        this.quantity = quantity;
    }
}
