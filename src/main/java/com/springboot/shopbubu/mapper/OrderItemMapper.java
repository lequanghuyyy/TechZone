package com.springboot.shopbubu.mapper;

import com.springboot.shopbubu.dto.OrderItemDto;
import com.springboot.shopbubu.entity.CartProductEntity;
import com.springboot.shopbubu.entity.OrderItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public OrderItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderItemDto convertToOrderItemDto(OrderItemEntity orderItemEntity) {
        OrderItemDto orderItemDto = modelMapper.map(orderItemEntity, OrderItemDto.class);
        orderItemDto.setProductId(Math.toIntExact(orderItemEntity.getProduct().getId()));
        return orderItemDto;
    }
    public OrderItemEntity convertToOrderItemEntity(OrderItemDto orderItemDto) {
        return modelMapper.map(orderItemDto, OrderItemEntity.class);
    }
    public List<OrderItemEntity> convertToOrderItemEntity(List<CartProductEntity> cartProductEntities) {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (CartProductEntity cartProductEntity : cartProductEntities) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setPrice(cartProductEntity.getCartPrice());
            orderItemEntity.setQuantity(cartProductEntity.getQuantity());
            orderItemEntity.setProduct(cartProductEntity.getProduct());
            orderItemEntities.add(orderItemEntity);
        }
        return orderItemEntities;
    }
}
