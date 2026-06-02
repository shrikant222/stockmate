package com.stockmate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class StockRequestDTO {

    @NotNull(message = "Purchase price is required")
    @DecimalMin(value = "0.0", message = "Purchase price cannot be negative"
    )
    private BigDecimal purchasePrice;

    @NotNull(message = "Selling price is required")
    @DecimalMin(value = "0.0", message = "Selling price cannot be negative"
    )
    private BigDecimal sellingPrice;

    @NotNull(message = "MRP is required")
    @DecimalMin(value = "0.0", message = "MRP cannot be negative"
    )
    private BigDecimal mrp;

    @NotBlank(message = "Batch number is required")
    @Size(max = 50, message = "Batch number cannot exceed 50 characters")
    private String batchNumber;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Manufacturing date cannot be future date")
    private LocalDate manufacturingDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Expiry date must be in future")
    private LocalDate expiryDate;

    @NotBlank(message = "Supplier name is required")
    @Size(max = 100,message = "Supplier name cannot exceed 100 characters")
    private String supplierName;

    @Pattern(
            regexp = "(^$)|(^\\d{10}$)|(^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$)",
            message = "Supplier contact must be a valid 10-digit mobile number or email address"
    )
    private String supplierContact;

    @NotBlank(message = "Received by field is required")
    @Size(max = 100, message = "Received by cannot exceed 100 characters")
    private String receivedBy;
}