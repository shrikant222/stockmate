package com.stockmate.dto;

import com.stockmate.enums.ProductCategory;
import com.stockmate.enums.ProductStatus;
import com.stockmate.enums.UnitOfMeasure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Product category is required")
    private ProductCategory category;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Product status is required")
    private ProductStatus status;

    @NotNull(message = "Product size or weight is required")
    @Size(max = 50, message = "Size/Weight description cannot exceed 50 characters")
    private String sizeOrWeight;

    @NotNull(message = "Unit of measure is required")
    private UnitOfMeasure unitOfMeasure;

}
