package com.practice.store.mapper;

import com.practice.store.dto.OrderDto;
import com.practice.store.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public Order convertToEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}
