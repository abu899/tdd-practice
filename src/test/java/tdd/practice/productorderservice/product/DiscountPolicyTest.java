package tdd.practice.productorderservice.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {

    @Test
    void applyDiscount() {
        int price = 1000;
        int discountedPrice = DiscountPolicy.NONE.applyDiscount(price);
        assertThat(discountedPrice).isEqualTo(price);
    }

    @Test
    void applyDiscount_1000() {
        int price = 2000;
        int discountedPrice = DiscountPolicy.FIX_1000.applyDiscount(price);
        assertThat(discountedPrice).isEqualTo(1000);
    }
}