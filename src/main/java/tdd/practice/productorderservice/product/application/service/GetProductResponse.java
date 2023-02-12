package tdd.practice.productorderservice.product.application.service;

import lombok.Getter;
import org.springframework.util.Assert;
import tdd.practice.productorderservice.product.domain.DiscountPolicy;

@Getter
public
class GetProductResponse {
    private Long id;
    private String name;
    private int price;
    private DiscountPolicy discountPolicy;

    public GetProductResponse(Long id, String name, int price, DiscountPolicy discountPolicy) {
        Assert.notNull(id, "상품 ID 는 필수");
        Assert.hasText(name, "상품명은 필수");
        Assert.notNull(discountPolicy, "할인 정책은 필수");

        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }
}
