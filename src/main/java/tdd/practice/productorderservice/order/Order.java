package tdd.practice.productorderservice.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import tdd.practice.productorderservice.product.Product;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;
    private int quantity;

    public Order(Product product, int quantity) {
        Assert.notNull(product, "상품 필수");
        Assert.isTrue(quantity > 0, "상품은 1개 이상");
        this.product = product;
        this.quantity = quantity;
    }
}
