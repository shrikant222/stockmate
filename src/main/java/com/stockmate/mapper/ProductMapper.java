package com.stockmate.mapper;

import com.stockmate.dto.ProductRequestDTO;
import com.stockmate.dto.ProductResponseDTO;
import com.stockmate.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    Product toEntity(ProductRequestDTO dto);

    ProductResponseDTO toResponseDto(Product entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "totalQuantity", ignore = true)
    @Mapping(target = "stockEntries", ignore = true)
    void updateEntityFromDto(ProductRequestDTO dto, @MappingTarget Product entity);

}
