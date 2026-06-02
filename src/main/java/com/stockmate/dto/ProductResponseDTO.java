package com.stockmate.dto;


import com.stockmate.enums.ProductCategory;
import com.stockmate.enums.ProductStatus;
import com.stockmate.enums.UnitOfMeasure;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private ProductCategory category;
    private String description;
    private ProductStatus status;
    private String sizeOrWeight;
    private UnitOfMeasure unitOfMeasure;
    private Integer totalQuantity;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime createdAt;
    private String createdBy;

}


