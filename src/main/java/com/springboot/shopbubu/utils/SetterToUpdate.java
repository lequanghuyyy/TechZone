package com.springboot.shopbubu.utils;

import com.springboot.shopbubu.dto.*;
import com.springboot.shopbubu.entity.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class SetterToUpdate {
    private SetterToUpdate() {};
    public static int calculateAge(Date birthDate) {
        LocalDate birthLocalDate = birthDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthLocalDate, currentDate).getYears();
    }
    public static void setCategory(CategoryDto categoryDto, CategoryEntity categoryEntity) {
        categoryEntity.setUpdatedAt(new Date());
        if (categoryDto.getCategoryName() != null){
            categoryEntity.setCategoryName(categoryDto.getCategoryName());
        }
        if (categoryDto.getDescription() != null){
            categoryEntity.setDescription(categoryDto.getDescription());
        }
        if (categoryDto.getImage() != null){
            categoryEntity.setImage(categoryDto.getImage());
        }
    }
    public static void setCustomerDetail(CustomerDetailEntity customerDetailEntity, CustomerDetailDto customerDetailDto) {
        customerDetailEntity.setUpdatedAt(new Date());
        if (customerDetailDto.getBirthday() != null){
            customerDetailEntity.setBirthday(customerDetailDto.getBirthday());
            customerDetailEntity.setAge(calculateAge(customerDetailDto.getBirthday()));
        }
        if (customerDetailDto.getAddress() != null){
            customerDetailEntity.setAddress(customerDetailDto.getAddress());
        }
        if (customerDetailDto.getCity() != null){
            customerDetailEntity.setCity(customerDetailDto.getCity());
        }
        if (customerDetailDto.getDescription() != null){
            customerDetailEntity.setDescription(customerDetailDto.getDescription());
        }
        if (customerDetailDto.getEmail() != null){
            customerDetailEntity.setEmail(customerDetailDto.getEmail());
        }
        if (customerDetailDto.getPhone() != null){
            customerDetailEntity.setPhone(customerDetailDto.getPhone());
        }
        if(customerDetailDto.getAvatar() != null){
            customerDetailEntity.setAvatar(customerDetailDto.getAvatar());
        }
    }
    public static void setOrder(OrderDto orderDto, OrderEntity orderEntity) {
        orderEntity.getOrderDetails().setUpdatedAt(new Date());
        if(orderDto.getShipAddress() != null){
            orderEntity.setShipAddress(orderDto.getShipAddress());
        }
        if(orderDto.getShipName() != null){
            orderEntity.setShipName(orderDto.getShipName());
        }
        if(orderDto.getShipAddress() != null){
            orderEntity.setShipAddress(orderDto.getShipAddress());
        }
        if(orderDto.getShipCity() != null){
            orderEntity.setShipCity(orderDto.getShipCity());
        }}
    public static void setProduct(ProductEntity productEntity, ProductDto productDto){
        productEntity.setUpdatedAt(new Date());
        if(productDto.getProductName() != null){
            productEntity.setProductName(productDto.getProductName());
        }
        if (productDto.getImage() != null){
            productEntity.setImage(productDto.getImage());
        }
        if (productDto.getPrice() != null){
            productEntity.setPrice(productDto.getPrice());
        }
        if (productDto.getNumberPurchase() != null){
            productEntity.setNumberPurchase(productDto.getNumberPurchase());
        }
        if (productDto.getProductDetail().getDescription() != null){
            productEntity.getProductDetail().setDescription(productDto.getProductDetail().getDescription());
        }
        if (productDto.getProductDetail().getStockQuantity() != null){
            productEntity.getProductDetail().setStockQuantity(productDto.getProductDetail().getStockQuantity());
        }
    }
}
