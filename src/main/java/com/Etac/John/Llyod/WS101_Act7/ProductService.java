package com.Etac.John.Llyod.WS101_Act7;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(3); // Starting after initial mock data

    // Initialize with mock data
    public ProductService() {
        products.add(new Product(1L, "Laptop Pro", 1299.99));
        products.add(new Product(2L, "Wireless Mouse", 29.99));
        products.add(new Product(3L, "Mechanical Keyboard", 89.99));
    }

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    // GET PRODUCT BY ID
    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    // CREATE NEW PRODUCT
    public Product createProduct(Product product) {
        Long newId = idCounter.incrementAndGet();
        product.setId(newId);
        products.add(product);
        return product;
    }

    // UPDATE EXISTING PRODUCT
    public Optional<Product> updateProduct(Long id, Product productDetails) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            return Optional.of(product);
        }
        return Optional.empty();
    }

    // DELETE PRODUCT
    public boolean deleteProduct(Long id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}