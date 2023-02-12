package tdd.practice.productorderservice.order;

import org.junit.jupiter.api.Test;
import tdd.practice.productorderservice.order.domain.Order;
import tdd.practice.productorderservice.product.domain.DiscountPolicy;
import tdd.practice.productorderservice.product.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void getTotalPrice() {
        Order order = new Order(new Product("상품", 1000, DiscountPolicy.NONE), 2);
        int totalPrice = order.getTotalPrice();
        assertThat(totalPrice).isEqualTo(2000);
    }

    @Test
    void getTotalPriceWithDiscount() {
        Order order = new Order(new Product("상품", 2000, DiscountPolicy.FIX_1000), 2);
        int totalPrice = order.getTotalPrice();
        assertThat(totalPrice).isEqualTo(2000);
    }
}