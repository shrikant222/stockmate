package com.stockmate.service;

import com.stockmate.dto.ProductRequestDTO;
import com.stockmate.dto.ProductResponseDTO;
import com.stockmate.exception.ResourceNotFoundException;
import com.stockmate.mapper.ProductMapper;
import com.stockmate.model.Product;
import com.stockmate.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private static final String PRODUCT_RESOURCE = "Product";
    private static final String FIELD_ID = "id";
    private static final String PRODUCT_RETURNED = "Returning {} products";
    private final ProductMapper productMapper;
    private final ProductRepository repository;

    public ProductResponseDTO addProduct(ProductRequestDTO requestDTO) {
        log.info("Creating new product");
        log.debug("ProductRequestDTO received: {}", requestDTO);
        Product product = productMapper.toEntity(requestDTO);
        product.setTotalQuantity(0);
        log.debug("Mapped Product entity before save: {}", product);
        Product saved = repository.save(product);
        log.info("Product created successfully with id={}", saved.getId());
        log.debug("Saved Product entity: {}", saved);
        return productMapper.toResponseDto(saved);
    }


    public List<ProductResponseDTO> getAllProducts() {
        log.info("Fetching all products");
        List<Product> entities = repository.findAll();
        log.debug("Fetched product entities: {}", entities);
        List<ProductResponseDTO> responseList = entities.stream()
                .map(productMapper::toResponseDto).toList();
        log.info(PRODUCT_RETURNED, responseList.size());
        return responseList;
    }

    public ProductResponseDTO getProductById(Long id) {
        log.info("Fetching product by id={}", id);
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_RESOURCE, FIELD_ID, id));
        log.debug("Fetched Product entity: {}", product);
        return productMapper.toResponseDto(product);
    }


    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        log.info("Updating product with id={}", id);
        log.debug("Update request DTO: {}", requestDTO);
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_RESOURCE, FIELD_ID, id));
        log.debug("Existing Product before update: {}", existing);
        productMapper.updateEntityFromDto(requestDTO, existing);
        log.debug("Product after applying DTO update: {}", existing);
        Product updated = repository.save(existing);
        log.info("Product updated successfully with id={}", updated.getId());
        log.debug("Updated Product entity: {}", updated);
        return productMapper.toResponseDto(updated);
    }

    public void deleteProduct(Long id) {
        log.warn("Deleting product with id={}", id);
        Product product = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                PRODUCT_RESOURCE,
                                FIELD_ID,
                                id
                        )
                );
        repository.delete(product);
        log.info("Product deleted successfully with id={}", id);
    }

}
