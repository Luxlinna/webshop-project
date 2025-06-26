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





// @Mapper(componentModel = "spring", uses = {CustomerMapper.class, OrderItemMapper.class})
// public interface OrderMapper {

//     OrderResponseDto toResponseDto(OrderEntity entity);

//     OrderEntity toEntity(OrderRequestDto dto);

//     List<OrderResponseDto> toResponseDtoList(List<OrderEntity> entities);

//     List<OrderItemEntity> toItemEntities(List<OrderItemDto> items);

//     @AfterMapping
//     default void mapAdditionalFieldsToEntity(OrderRequestDto dto, @MappingTarget OrderEntity entity) {
//         entity.setOrderDate(LocalDateTime.now());
//         // Set default status if not set
//         if (entity.getStatus() == null) {
//             entity.setStatus("PENDING");
//         }
//     }
// }




// @Mapper(componentModel = "spring", uses = {CustomerMapper.class, OrderItemMapper.class})
// public interface OrderMapper {

//     // Map from Order entity to Response DTO
//     OrderResponseDto toResponseDto(OrderEntity entity);

//     // Map from Request DTO to Order entity
//     OrderEntity toEntity(OrderRequestDto dto);

//     // If you need List mapping (optional)
//     List<OrderResponseDto> toResponseDtoList(List<OrderEntity> entities);

//     @AfterMapping
//     default void mapAdditionalFieldsToEntity(OrderRequestDto dto, @MappingTarget OrderEntity entity) {
//         // map any fields that MapStruct cannot handle automatically if needed
//         entity.setOrderDate(LocalDateTime.now());
//     }
// }

