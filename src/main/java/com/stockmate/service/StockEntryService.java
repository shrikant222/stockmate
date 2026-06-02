package com.stockmate.service;

import com.stockmate.dto.StockRequestDTO;
import com.stockmate.dto.StockResponseDTO;
import com.stockmate.dto.StockUpdateDTO;
import com.stockmate.exception.ResourceNotFoundException;
import com.stockmate.mapper.StockMapper;
import com.stockmate.model.Product;
import com.stockmate.model.StockEntry;
import com.stockmate.repository.ProductRepository;
import com.stockmate.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockEntryService {

    private static final String PRODUCT_RESOURCE = "Product";
    private static final String FIELD_ID = "id";
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final StockMapper stockMapper;

    public StockResponseDTO createStockEntry(StockRequestDTO stock, Long productId) {
        log.debug("Creating stock entry for productId: {}, quantity: {}", productId, stock.getQuantity());
        Product product = productRepository.findById(productId).orElseThrow(() -> {
            log.error("Product not found with id: {}", productId);
            return new ResourceNotFoundException(PRODUCT_RESOURCE, FIELD_ID, productId);
        });

        Integer updatedQuantity = product.getTotalQuantity() + stock.getQuantity();
        product.setTotalQuantity(updatedQuantity);
        StockEntry stockEntry = stockMapper.toEntity(stock);
        stockEntry.setProduct(product);
        productRepository.save(product);
        StockEntry savedStockEntry = stockRepository.save(stockEntry);
        log.info("Stock entry created successfully for productId: {}, stockEntryId: {}, updatedQuantity: {}",
                productId, savedStockEntry.getId(), updatedQuantity);
        return stockMapper.toResponseDto(savedStockEntry);
    }

    public List<StockResponseDTO> getStocksByProductId(Long productId) {
        log.debug("Fetching stock entries for productId: {}", productId);
        List<StockResponseDTO> stockList = stockRepository.findByProductId(productId)
                .stream()
                .map(stockMapper::toResponseDto)
                .toList();
        log.debug("Retrieved {} stock entries for productId: {}", stockList.size(), productId);
        return stockList;
    }


    @Transactional
    public StockResponseDTO updateStockEntry(Long productId, Long stockId, StockUpdateDTO stockDTO) {
        log.info("Updating stock entry. productId: {}, stockId: {}",productId, stockId);

        StockEntry stockEntry = stockRepository.findById(stockId)
                .orElseThrow(() -> {log.error("Stock entry not found with id: {}", stockId);
                    return new ResourceNotFoundException("Stock", FIELD_ID, stockId                    );
                });

        if (!stockEntry.getProduct().getId().equals(productId)) {
            log.error("Stock {} does not belong to product {}",stockId, productId);
            throw new ResourceNotFoundException(PRODUCT_RESOURCE, FIELD_ID, productId
            );
        }

        Integer oldQuantity = stockEntry.getQuantity();
        Integer updatedQuantity = stockDTO.getQuantity() != null ? stockDTO.getQuantity() : oldQuantity;
        if (!Objects.equals(oldQuantity, updatedQuantity)) {
            Product product = stockEntry.getProduct();
            Integer oldTotalQuantity = product.getTotalQuantity();
            Integer newTotalQuantity =oldTotalQuantity - oldQuantity + updatedQuantity;
            if (newTotalQuantity < 0) {
                log.error("Invalid total quantity calculated for productId: {}",productId);
                throw new IllegalArgumentException("Total quantity cannot be negative");
            }
            product.setTotalQuantity(newTotalQuantity);
            log.debug("Updated total quantity for productId: {} from {} to {}",
                    productId, oldTotalQuantity, newTotalQuantity
            );
        }

        stockMapper.updateEntityFromDto(stockDTO, stockEntry);
        StockEntry updatedStock = stockRepository.save(stockEntry);
        log.info("Stock entry updated successfully. stockId: {}",stockId);
        return stockMapper.toResponseDto(updatedStock);
    }
}
