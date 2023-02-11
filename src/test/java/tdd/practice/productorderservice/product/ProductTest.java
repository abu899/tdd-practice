package tdd.practice.productorderservice.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void update() {
        Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
        product.update("상품 수정", 2000, DiscountPolicy.NONE);

        assertThat(product.getName()).isEqualTo("상품 수정");
        assertThat(product.getPrice()).isEqualTo(2000);
    }

    @Test
    void discount_none() {
        Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
        int discountedPrice = product.getDisCountPrice();
        assertThat(discountedPrice).isEqualTo(1000);
    }

    @Test
    void discount_1000() {
        Product product = new Product("상품명", 2000, DiscountPolicy.FIX_1000);
        int discountedPrice = product.getDisCountPrice();
        assertThat(discountedPrice).isEqualTo(1000);
    }

    @Test
    void over_discount() {
        Product product = new Product("상품명", 500, DiscountPolicy.FIX_1000);
        int discountedPrice = product.getDisCountPrice();
        assertThat(discountedPrice).isEqualTo(0);
    }
}