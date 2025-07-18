package com.example.webshop.mapper;

import com.example.webshop.entity.OrderItemEntity;
import com.example.webshop.dto.OrderItemDto;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemDto toDto(OrderItemEntity entity);

    OrderItemEntity toEntity(OrderItemDto dto);

    List<OrderItemDto> toDtos(List<OrderItemEntity> entities);

    List<OrderItemEntity> toEntities(List<OrderItemDto> dtos);
}


