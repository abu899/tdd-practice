package tdd.practice.productorderservice.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor
class UpdateProductRequest {
    private String name;
    private int price;
    private DiscountPolicy discountPolicy;

    public UpdateProductRequest(String name, int price, DiscountPolicy discountPolicy) {
        Assert.hasText(name, "상품명은 필수");
        Assert.isTrue(price > 0, "가격은 0보다 커야함");
        Assert.notNull(discountPolicy, "할인 정책은 필수");

        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }
}
