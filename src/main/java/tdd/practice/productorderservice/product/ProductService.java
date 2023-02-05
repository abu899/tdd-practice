package tdd.practice.productorderservice.product;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductService {
    private final ProductPort productPort;

    ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @Transactional
    public void addProduct(AddProductRequest request) {
        Product product = new Product(request.getName(), request.getPrice(), request.getDiscountPolicy());
        productPort.save(product);
    }
}
