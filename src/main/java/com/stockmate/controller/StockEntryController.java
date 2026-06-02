package com.stockmate.controller;

import com.stockmate.dto.StockRequestDTO;
import com.stockmate.dto.StockResponseDTO;
import com.stockmate.dto.StockUpdateDTO;
import com.stockmate.service.StockEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockEntryController {

    private final StockEntryService stockService;

    @PostMapping("/{productId}/")
    public ResponseEntity<StockResponseDTO> addStock(@Valid @RequestBody StockRequestDTO stock, @PathVariable Long productId) {
        return ResponseEntity.ok(stockService.createStockEntry(stock, productId));
    }

    @GetMapping("/{productId}/")
    public ResponseEntity<List<StockResponseDTO>> getStockForProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(stockService.getStocksByProductId(productId));
    }

    @PutMapping("/{productId}/{stockId}")
    public ResponseEntity<StockResponseDTO> updateProductStock(@PathVariable Long productId, @PathVariable Long stockId, @RequestBody StockUpdateDTO stockDTO) {
        return ResponseEntity.ok(stockService.updateStockEntry(productId, stockId, stockDTO));
    }


}
