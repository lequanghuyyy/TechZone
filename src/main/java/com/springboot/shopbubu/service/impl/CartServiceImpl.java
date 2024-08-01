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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        return null;
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
        BigDecimal total = BigDecimal.ZERO;
        Optional<ProductEntity> productEntity = productRepository.findById(Long.valueOf(cartProductDto.getProductId()));
        BigDecimal a = productEntity.get().getPrice();
        total = total.add(a).multiply(BigDecimal.valueOf(cartProductDto.getQuantity()));
        return total;
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
            cartProductEntity.setCart(cartEntity);
            ProductEntity productEntity = productRepository.findById(Long.valueOf(cartProductDto.getProductId())).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + cartProductDto.getProductId()));
            cartProductEntity.setProduct(productEntity);
            cartProductEntity.setCartPrice(productEntity.getPrice().add(BigDecimal.valueOf(cartProductEntity.getQuantity())));
            cartProductEntityList.add(cartProductEntity);
        }
        return cartProductEntityList;
    }
}
