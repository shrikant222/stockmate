package com.stockmate.repository;

import com.stockmate.model.StockEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<StockEntry, Long> {

    List<StockEntry> findByProductId(Long productId);
}
