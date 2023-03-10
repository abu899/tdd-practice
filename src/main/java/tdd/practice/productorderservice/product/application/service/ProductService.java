package tdd.practice.productorderservice.product.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tdd.practice.productorderservice.product.application.port.ProductPort;
import tdd.practice.productorderservice.product.domain.Product;

@RestController
@RequestMapping("/products")
public class ProductService {
    private final ProductPort productPort;

    ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> addProduct(@RequestBody AddProductRequest request) {
        Product product = new Product(request.getName(), request.getPrice(), request.getDiscountPolicy());
        productPort.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable Long productId) {
        Product product = productPort.getProduct(productId);
        GetProductResponse response = new GetProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDiscountPolicy());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest request) {
        Product product = productPort.getProduct(productId);
        product.update(request.getName(), request.getPrice(), request.getDiscountPolicy());
        productPort.save(product);
        return ResponseEntity.ok().build();
    }


}
