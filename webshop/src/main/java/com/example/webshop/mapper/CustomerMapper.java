package com.example.webshop.mapper;

import com.example.webshop.entity.CustomerEntity;
import com.example.webshop.entity.OrderItemEntity;
import com.example.webshop.dto.CustomerInfoDto;
import com.example.webshop.dto.OrderItemDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity toEntity(CustomerInfoDto dto);
    CustomerInfoDto toDto(CustomerEntity entity);
    
}
