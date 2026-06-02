package com.stockmate.model;

import com.stockmate.enums.ProductCategory;
import com.stockmate.enums.ProductStatus;
import com.stockmate.enums.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column()
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(nullable = false)
    private String sizeOrWeight;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;

    @Column(nullable = false)
    private Integer totalQuantity;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockEntry> stockEntries = new ArrayList<>();

}
