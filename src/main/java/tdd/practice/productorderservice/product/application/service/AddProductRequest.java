package tdd.practice.productorderservice.product.application.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.practice.productorderservice.product.domain.DiscountPolicy;

@Getter
@NoArgsConstructor
public class AddProductRequest {
    private String name;
    private int price;
    private DiscountPolicy discountPolicy;

    public AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {
        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }
}
