package com.example.webshop.mapper;

import com.example.webshop.entity.OrderEntity;
import com.example.webshop.dto.OrderRequestDto;
import com.example.webshop.dto.OrderResponseDto;
import com.example.webshop.entity.OrderItemEntity;
import com.example.webshop.dto.OrderItemDto;

import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;


@Mapper(componentModel = "spring", uses = {CustomerMapper.class, OrderItemMapper.class})
public interface OrderMapper {

    // Explicitly map customer.address to orderEntity.address
    // @Mapping(target = "address", source = "customer.address")
    OrderResponseDto toResponseDto(OrderEntity entity);

    OrderEntity toEntity(OrderRequestDto dto);

    List<OrderResponseDto> toResponseDtoList(List<OrderEntity> entities);

    List<OrderItemEntity> toItemEntities(List<OrderItemDto> items);

    @AfterMapping
    default void mapAdditionalFieldsToEntity(OrderRequestDto dto, @MappingTarget OrderEntity entity) {
        entity.setOrderDate(LocalDateTime.now());
        if (entity.getStatus() == null) {
            entity.setStatus("PENDING");
        }
    }
}

