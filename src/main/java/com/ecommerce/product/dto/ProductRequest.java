// src/main/java/com/ecommerce/product/dto/ProductRequest.java
package com.ecommerce.product.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @Positive(message = "Price must be positive")
    private Double price;

    @Min(value = 0)
    private Integer stockQuantity = 0;

    @NotNull(message = "Category ID is required")
    private Long categoryId;  // Use ID to link to existing category
}