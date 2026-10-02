package com.ecommerce.product.runner;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.repository.CategoryRepository;
import com.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.findAll().isEmpty()) {
            log.info("Inserting initial categories...");

            Category electronics = categoryRepository.save(Category.builder()
                    .name("Electronics")
                    .description("Gadgets and devices")
                    .build());

            Category fashion = categoryRepository.save(Category.builder()
                    .name("Fashion")
                    .description("Clothing and accessories")
                    .build());

            log.info("Inserting initial products with relations...");

            productService.createProduct(new ProductRequest("iPhone 15", "Latest Apple smartphone", 999.99, 50, electronics.getId()));
            productService.createProduct(new ProductRequest("MacBook Pro", "M3 Pro chip", 2399.00, 20, electronics.getId()));
            productService.createProduct(new ProductRequest("Nike Air Max", "Running shoes", 179.99, 100, fashion.getId()));
            productService.createProduct(new ProductRequest("Sony WH-1000XM5", "Noise cancelling headphones", 399.99, 30, electronics.getId()));

            log.info("Initial data inserted successfully!");
        }
    }
}