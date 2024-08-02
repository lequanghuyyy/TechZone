package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.constant.CartStatus;
import com.springboot.shopbubu.dto.CartDto;
import com.springboot.shopbubu.dto.CartProductDto;
import com.springboot.shopbubu.dto.ProductDto;
import com.springboot.shopbubu.entity.CartEntity;
import com.springboot.shopbubu.entity.CartProductEntity;
import com.springboot.shopbubu.entity.CustomerEntity;
import com.springboot.shopbubu.entity.ProductEntity;
import com.springboot.shopbubu.mapper.CartMapper;
import com.springboot.shopbubu.mapper.CartProductMapper;
import com.springboot.shopbubu.mapper.CustomerMapper;
import com.springboot.shopbubu.repository.CartProductRepository;
import com.springboot.shopbubu.repository.CartRepository;
import com.springboot.shopbubu.repository.CustomerRepository;
import com.springboot.shopbubu.repository.ProductRepository;
import com.springboot.shopbubu.security.CustomUserDetails;
import com.springboot.shopbubu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;
    private final CartMapper cartMapper;
    private final CartProductMapper cartProductMapper;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, CartProductRepository cartProductRepository, CartMapper cartMapper, CartProductMapper cartProductMapper, CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartProductRepository = cartProductRepository;
        this.cartMapper = cartMapper;
        this.cartProductMapper = cartProductMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CartDto> findALl() {
        return cartRepository.findAll().stream().map(cartMapper::convertToCartDto).toList();
    }

    @Override
    public CartDto findById(Long id) {
        return cartRepository.findById(id).map(cartMapper::convertToCartDto).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public CartDto create(CartDto cartDto) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(getIdUserCurrent());
        cartDto.setTotalProduct(getTotalProduct(cartDto.getCartProducts()));
        cartDto.setCartStatus(CartStatus.UNPAID);
        CartEntity cartEntity = cartMapper.convertToCartEntity(cartDto);
        cartEntity.setCartProducts(convertListCartProductEntity(cartDto.getCartProducts(),cartEntity));
        customerEntity.ifPresent(cartEntity::setCustomer);
        cartRepository.save(cartEntity);
        return cartMapper.convertToCartDto(cartEntity);
    }

    @Override
    public CartDto update(CartDto cartDto) {
        CartEntity cartEntity = cartRepository.findById(cartDto.getId()).orElseThrow(NoSuchElementException::new);
        if (!Objects.equals(cartEntity.getCustomer().getUser().getId(), getIdUserCurrent())) {
            throw new AccessDeniedException("You do not have permission to update this cart.");
        }
        List<CartProductEntity> cartProductEntityList = convertListCartProductEntity(cartDto.getCartProducts(),cartEntity);
        cartEntity.setCartProducts(cartProductEntityList);
        cartEntity.setUpdatedAt(new Date());
        cartEntity.setTotalProduct(getTotalProduct(cartDto.getCartProducts()));
        return cartMapper.convertToCartDto(cartEntity);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
    public Long getIdUserCurrent(){
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser().getId();
    }
    public BigDecimal getTotalPriceCartProduct(CartProductDto cartProductDto) {
        Optional<ProductEntity> productEntity = productRepository.findById(Long.valueOf(cartProductDto.getProductId()));
        BigDecimal a = productEntity.get().getPrice();
        return a.multiply(BigDecimal.valueOf(cartProductDto.getQuantity()));
    }
    public BigDecimal getTotalProduct(List<CartProductDto> cartProductDtoList) {
        BigDecimal totalProduct = BigDecimal.ZERO;
        for (CartProductDto cartProductDto : cartProductDtoList) {
            totalProduct = totalProduct.add(getTotalPriceCartProduct(cartProductDto));
        }
        return totalProduct;
    }
    public List<CartProductEntity> convertListCartProductEntity(List<CartProductDto> cartProductDtoList,CartEntity cartEntity) {
        List<CartProductEntity> cartProductEntityList = new ArrayList<>();
        for (CartProductDto cartProductDto : cartProductDtoList) {
            CartProductEntity cartProductEntity = cartProductMapper.convertToCartProductEntity(cartProductDto);
            cartProductEntity.setId(cartProductDto.getProductId());
            cartProductEntity.setCart(cartEntity);
            ProductEntity productEntity = productRepository.findById(Long.valueOf(cartProductDto.getProductId())).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + cartProductDto.getProductId()));
            cartProductEntity.setProduct(productEntity);
            cartProductEntity.setCartPrice(productEntity.getPrice().add(BigDecimal.valueOf(cartProductEntity.getQuantity())));
            cartProductEntityList.add(cartProductEntity);
        }
        return cartProductEntityList;
    }
}
