package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.constant.CartStatus;
import com.springboot.shopbubu.constant.OrderStatus;
import com.springboot.shopbubu.dto.CartProductDto;
import com.springboot.shopbubu.dto.OrderDto;
import com.springboot.shopbubu.dto.OrderItemDto;
import com.springboot.shopbubu.dto.request.PaymentRequest;
import com.springboot.shopbubu.entity.*;
import com.springboot.shopbubu.mapper.OrderDetailMapper;
import com.springboot.shopbubu.mapper.OrderItemMapper;
import com.springboot.shopbubu.mapper.OrderMapper;
import com.springboot.shopbubu.repository.CartRepository;
import com.springboot.shopbubu.repository.CustomerRepository;
import com.springboot.shopbubu.repository.OrderDetailRepository;
import com.springboot.shopbubu.repository.OrderRepository;
import com.springboot.shopbubu.security.CustomUserDetails;
import com.springboot.shopbubu.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService  {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final OrderItemMapper orderItemMapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, OrderDetailMapper orderDetailMapper, OrderDetailRepository orderDetailRepository, CartRepository cartRepository, OrderItemMapper orderItemMapper, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.orderDetailRepository = orderDetailRepository;
        this.cartRepository = cartRepository;
        this.orderItemMapper = orderItemMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::convertToOrderDto).toList();
    }

    @Transactional
    @Override
    public OrderDto create(PaymentRequest paymentRequest) {
        CartEntity cartEntity = cartRepository.findById(paymentRequest.getCartId()).orElseThrow(NoSuchElementException::new);
        OrderEntity orderEntity = orderMapper.convertToOrderEntity(paymentRequest.getOrderDto());
        orderEntity.setSumPrice(cartEntity.getTotalProduct());
        orderEntity.setOrderDate(new Date());

        List<OrderItemEntity> orderItemEntities = orderItemMapper.convertToOrderItemEntity(cartEntity.getCartProducts());
        orderEntity.setOrderItem(orderItemEntities);

        OrderDetailEntity orderDetailEntity = orderDetailMapper.convertToOrderDetailEntity(paymentRequest.getOrderDto().getOrderDetails());
        orderDetailEntity.setQuantity(sumOfProduct(cartEntity.getCartProducts()));
        orderEntity.setOrderDetails(orderDetailEntity);
        orderDetailEntity.setOrderEntity(orderEntity);

        orderEntity.setOrderStatus(OrderStatus.SHIPPING);
        Optional<CustomerEntity> customerEntity = customerRepository.findById(getIdUserCurrent());
        orderEntity.setCustomer(customerEntity.get());

        OrderEntity finalOrderEntity = orderEntity;
        orderItemEntities.forEach(orderItemEntity -> orderItemEntity.setOrder(finalOrderEntity));

        orderEntity = orderRepository.save(orderEntity);
        cartEntity.setCartStatus(CartStatus.PAID);
        cartRepository.save(cartEntity);

        return orderMapper.convertToOrderDto(orderEntity);
    }


    @Override
    public OrderDto findById(Long id) {
        return orderRepository
                .findById(id)
                .map(orderMapper::convertToOrderDto)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Long getIdUserCurrent(){
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser().getId();
    }
    public int sumOfProduct(List<CartProductEntity> cartProductEntities) {
        int sum = 0;
        for (CartProductEntity cartProductEntity : cartProductEntities) {
            sum = sum + cartProductEntity.getQuantity();
        }
        return sum;
    }
}
