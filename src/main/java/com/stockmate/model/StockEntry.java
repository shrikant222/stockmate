package com.stockmate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "stock_entry")
public class StockEntry extends BaseEntity {

    private String batchNumber;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal purchasePrice = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal sellingPrice = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal mrp = BigDecimal.ZERO;

    private LocalDate manufacturingDate;

    private LocalDate expiryDate;

    private String supplierName;

    private String supplierContact;

    @Column(nullable = false)
    private String receivedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
