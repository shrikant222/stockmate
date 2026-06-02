package com.stockmate.mapper;

import com.stockmate.dto.*;
import com.stockmate.model.Product;
import com.stockmate.model.StockEntry;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StockMapper {


    StockEntry toEntity(StockRequestDTO dto);

    StockResponseDTO toResponseDto(StockEntry entity);

    void updateEntityFromDto(StockUpdateDTO dto, @MappingTarget StockEntry entity);
}
